package cn.stt.pager.mybatis;


import java.io.Serializable;

/**
 * 数据Entity类
 * @author 
 * @version 2013-05-28
 */
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long id;		// 编号
	
	public IdEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public boolean isNew(){
        return this.id == null;
    }
}
