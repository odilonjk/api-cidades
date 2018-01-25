package kroger.odilonj.api.vo;

import kroger.odilonj.api.enums.ColumnType;
import kroger.odilonj.api.utils.StringUtil;

public class ColumnVO {

	private final String name;
	
	private final ColumnType type;

	public ColumnVO(String name, ColumnType type) {
		this.name = StringUtil.stringAsCamelCase(name);
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public ColumnType getType() {
		return type;
	}
	
}
