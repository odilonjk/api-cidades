package kroger.odilonj.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import kroger.odilonj.api.entity.Cidade;
import kroger.odilonj.api.entity.QCidade;
import kroger.odilonj.api.enums.ColumnType;
import kroger.odilonj.api.utils.QueryUtil;
import kroger.odilonj.api.vo.CidadeVO;
import kroger.odilonj.api.vo.ColumnVO;
import kroger.odilonj.api.vo.DistanceVO;


@Stateless
@PermitAll
public class CidadeService {

	@PersistenceContext
	private EntityManager em;
	
	private QCidade cidade = QCidade.cidade;

	public List<Cidade> findCapitals(String order) throws Exception {
		JPAQuery<Cidade> query = new JPAQuery<Cidade>(em).from(cidade)
			.where(cidade.capital.isTrue());
		if(order.equals("asc"))
			return query.orderBy(cidade.name.asc()).fetch();
		else if(order.equals("desc"))
			return query.orderBy(cidade.name.desc()).fetch();
		else
			throw new Exception("Ordenação inválida. Tipos aceitos: asc, desc.");
	}

	public HashMap<String, Long> findTotalByStates() {
		HashMap<String, Long> result = new HashMap<>();
		List<Tuple> fetch = new JPAQuery<>(em).from(cidade)
			.select(cidade.uf, cidade.name.count())
			.groupBy(cidade.uf)
			.fetch();
		for(Tuple t : fetch) {
			result.put(t.get(0, String.class), t.get(1, Long.class));
		}
		return result;
	}

	public Long findTotalRecords() {
		return new JPAQuery<>(em).from(cidade)
				.select(cidade)
				.fetchCount();
	}

	public Cidade find(Integer ibgeId) {
		return new JPAQuery<Cidade>(em).from(cidade)
			.where(cidade.ibgeId.eq(ibgeId))
			.fetchOne();
	}
	
	public void delete(Integer ibgeId) {
		new JPADeleteClause(em, cidade)
			.where(cidade.ibgeId.eq(ibgeId))
			.execute();
	}

	public void register(Cidade cidade) {
		em.persist(cidade);
	}
	
	public List<String> findCitiesByState(String uf) {
		return new JPAQuery<>(em).from(cidade)
			.select(cidade.name)
			.where(cidade.uf.toLowerCase().eq(uf.toLowerCase()))
			.orderBy(cidade.name.asc())
			.fetch();
	}

	public HashMap<String, Long> findExtremeStates() {
		HashMap<String, Long> result = new HashMap<>();
		HashMap<String, Long> states = findTotalByStates();
		
		Entry<String, Long> max = getMaxEntry(states);
		result.put(max.getKey(), max.getValue());
		Entry<String, Long> min = getMinEntry(states);
		result.put(min.getKey(), min.getValue());
		
		return result;
	}

	private Entry<String, Long> getMinEntry(HashMap<String, Long> states) {
		return states.entrySet().stream()
				.min((s1, s2) -> s1.getValue().compareTo(s2.getValue())).get();
	}

	private Entry<String, Long> getMaxEntry(HashMap<String, Long> states) {
		return states.entrySet().stream()
				.max((s1, s2) -> s1.getValue().compareTo(s2.getValue())).get();
	}


	public DistanceVO findMoreDistant() {
		List<CidadeVO> cidades = new JPAQuery<CidadeVO>(em).from(cidade)
				.select(Projections.constructor(CidadeVO.class, cidade.name, cidade.lon, cidade.lat))
				.fetch();
		List<DistanceVO> distances = cidades.stream().map(c -> getDistances(c, cidades)).collect(Collectors.toList());
		return getBiggestDistance(distances);
	}

	private DistanceVO getDistances(CidadeVO c, List<CidadeVO> cidades) {
		List<DistanceVO> distances = new ArrayList<>();
		cidades.stream()
			.filter(ci -> !ci.getName().equals(c.getName()))
			.forEach(ci -> distances.add(new DistanceVO(c.getName(), ci.getName(), calculate(c, ci))));
		return getBiggestDistance(distances);
	}

	private DistanceVO getBiggestDistance(List<DistanceVO> distances) {
		return distances.stream()
				.sorted((d1, d2) -> Double.compare(d2.getDistancia(), d1.getDistancia()))
				.findFirst()
				.get();
	}

	private double calculate(CidadeVO c, CidadeVO ci) {
		double cRadiansLat = Math.toRadians(c.getLat());
		double ciRadiansLat = Math.toRadians(ci.getLat());
		double deltaRadiansLongitude = Math.toRadians(c.getLon() - c.getLon());
		return Math.acos(Math.cos(cRadiansLat) * Math.cos(ciRadiansLat)
			* Math.cos(deltaRadiansLongitude) + Math.sin(cRadiansLat)
			* Math.sin(ciRadiansLat)) * 6371;
	}

	public Long findTotalRecordsByColumn(String column) {
		PathBuilder<Object> path = QueryUtil.getPath(column);
		return new JPAQuery<>(em).from(cidade)
				.distinct()
				.select(path)
				.fetchCount();
	}
	
	public List<Object> search(String column, String filter) throws Exception {
		ColumnVO columnVO = QueryUtil.getColumnVO(column);
		 JPAQuery<Object> query = new JPAQuery<>(em).from(cidade)
			.select(QueryUtil.getPath(columnVO.getName()));
		
		 if(columnVO.getType().equals(ColumnType.STRING)) 
			 return query.where(QueryUtil.getStringPath(columnVO.getName()).containsIgnoreCase(filter)).fetch();
		 return query.where(QueryUtil.getPath(columnVO.getName()).eq(Integer.valueOf(filter))).fetch();
	}

}
