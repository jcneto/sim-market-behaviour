package app.persistence.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable>{

	public T save(T object);
	public T load(PK pk);
	public void update(T object);
    public void delete(T object);
    public List<T> getList();
    
}