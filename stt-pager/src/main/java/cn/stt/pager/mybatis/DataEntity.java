package cn.stt.pager.mybatis;

import java.io.Serializable;

/**
 * 数据Entity类
 * @author 
 * @version 2013-05-28
 */
public abstract class DataEntity<T> extends BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String remark;	// 备注
	protected Long createTime;	// 创建日期
	protected String createUid;// 创建者
	protected Long updatUid;	// 更新者
	protected Long updateTime;// 更新日期
	protected String flag; // 删除标记（0：正常；1：删除；2：审核）

	public DataEntity() {
		super();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public Long getUpdatUid() {
		return updatUid;
	}

	public void setUpdatUid(Long updatUid) {
		this.updatUid = updatUid;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}
