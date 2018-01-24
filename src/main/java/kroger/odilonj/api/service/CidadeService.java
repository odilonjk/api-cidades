package kroger.odilonj.api.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import kroger.odilonj.api.entity.Cidade;
import kroger.odilonj.api.entity.QCidade;


@Stateless
@PermitAll
public class CidadeService {

	@PersistenceContext
	private EntityManager em;
	
	private QCidade cidade = QCidade.cidade;

	public List<Cidade> findCapitals() {
		return new JPAQuery<Cidade>(em).from(cidade)
			.where(cidade.capital.isTrue())
			.orderBy(cidade.name.asc())
			.fetch();
	}

	public HashMap<Long,String> findTotalByStates() {
		HashMap<Long, String> result = new HashMap<>();
		List<Tuple> fetch = new JPAQuery<>(em).from(cidade)
			.select(cidade.name.count(), cidade.uf)
			.groupBy(cidade.uf)
			.fetch();
		for(Tuple t : fetch) {
			result.put(t.get(0, Long.class), t.get(1, String.class));
		}
		return result;
	}

	public Long findTotalRecords() {
		return null;
//		return new JPAQuery<>(em).from(cidade)
//				.select(cidade.count())
//				.fetch();
	}

	public void delete(Integer ibgeId) {
		new JPADeleteClause(em, cidade)
			.where(cidade.ibgeId.eq(ibgeId))
			.execute();
	}

	public void register(Cidade cidade) {
		
	}
	
}
