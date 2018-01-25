package kroger.odilonj.api.utils;


import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import kroger.odilonj.api.entity.Cidade;
import kroger.odilonj.api.enums.ColumnType;
import kroger.odilonj.api.vo.ColumnVO;

public class QueryUtil {

	public static PathBuilder<Object> getPath(String columnName) {
		PathBuilder<Cidade> entityPath = new PathBuilder<>(Cidade.class, "cidade"); 
		return entityPath.get(columnName);
	}

	public static StringPath getStringPath(String columnName) {
		PathBuilder<Cidade> entityPath = new PathBuilder<>(Cidade.class, "cidade"); 
		return entityPath.getString(columnName);
	}

	public static ColumnVO getColumnVO(String column) throws Exception {
		switch(column) {
			case "name":
			case "uf":
			case "no_accents":
			case "alternative_names":
			case "mesoregion":
			case "microregion":
				return new ColumnVO(column, ColumnType.STRING);
			case "lon":
			case "lat":
			case "ibge_id":
				return new ColumnVO(column, ColumnType.NUMBER);
			case "capital":
				return new ColumnVO(column, ColumnType.BOOL);
			default:
				throw new Exception("Coluna inválida.");
		}
	}
	
}
