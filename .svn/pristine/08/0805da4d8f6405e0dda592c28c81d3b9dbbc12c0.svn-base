/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.server.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;


import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

/**
 * DAO layer as recommended by 
 * http://code.google.com/p/objectify-appengine/wiki/BestPractice
 * and
 * http://turbomanage.wordpress.com/2010/02/09/generic-dao-for-objectify-2/
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public class ObjectifyGenericDao<T> extends DAOBase {

	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;

	static {
		//--------------
		// initialize Objectify and register classes
		ObjectifyService.register(User.class );
		ObjectifyService.register(Course.class );
		ObjectifyService.register(Member.class );
		ObjectifyService.register(CourseLink.class );
		ObjectifyService.register(Session.class );
		ObjectifyService.register(School.class );
		ObjectifyService.register(ContactInfo.class );
		ObjectifyService.register(SessionInvite.class );
	}


	protected Class<T> clazz;

	/**
	 * We've got to get the associated domain class somehow
	 *
	 * @param clazz
	 */
	protected ObjectifyGenericDao(Class<T> clazz)
	{
		this.clazz = clazz;
		//		clazz = ((Class) ((ParameterizedType) getClass()
		//		.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@SuppressWarnings("unchecked")
	protected ObjectifyGenericDao()
	{
		clazz = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public Key<T> put(T entity)

	{
		return ofy().put(entity);
	}

	public Map<Key<T>, T> putAll(Iterable<T> entities)
	{
		return ofy().put(entities);
	}

	public void delete(T entity)
	{
		ofy().delete(entity);
	}

	public void deleteKey(Key<T> entityKey)
	{
		ofy().delete(entityKey);
	}

	public void deleteAll(Iterable<T> entities)
	{
		ofy().delete(entities);
	}

	public void deleteKeys(Iterable<Key<T>> keys)
	{
		ofy().delete(keys);
	}

	public T get(Long id) throws EntityNotFoundException
	{
		return ofy().get(this.clazz, id);
	}

	public T get(Key<T> key) throws EntityNotFoundException
	{
		return ofy().get(key);
	}
	
	/**
	 * Convenience method to get all objects matching a single property
	 *
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 */
	public T getByProperty(String propName, Object propValue)
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.get();
	}

	public List<T> listByProperty(String propName, Object propValue)
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.list();
	}

	public List<Key<T>> listKeysByProperty(String propName, Object propValue)
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.listKeys();
	}

	public T getByExample(T exampleObj)
	{
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		Iterable<T> iterableResults = queryByExample.list();
		Iterator<T> i = iterableResults.iterator();
		T obj = i.next();
		if (i.hasNext())
			throw new RuntimeException("Too many results");
		return obj;
	}

	public List<T> listByExample(T exampleObj)
	{
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return queryByExample.list();
	}


	public Key<T> getKey(Long id)
	{
		return new Key<T>(this.clazz, id);
	}

	public Key<T> key(T obj)
	{
		return ObjectifyService.factory().getKey(obj);
	}

	public List<T> listChildren(Object parent)
	{
		return ofy().query(clazz).ancestor(parent).list();
	}

	public List<Key<T>> listChildKeys(Object parent)
	{
		return ofy().query(clazz).ancestor(parent).listKeys();
	}


	//	private List<T> asList(Iterable<T> iterable)
	//	{
	//		ArrayList<T> list = new ArrayList<T>();
	//		for (T t : iterable)
	//		{
	//			list.add(t);
	//		}
	//		return list;
	//	}

	//	private List<Key<T>> asKeyList(Iterable<Key<T>> iterableKeys)
	//	{
	//		ArrayList<Key<T>> keys = new ArrayList<Key<T>>();
	//		for (Key<T> key : iterableKeys)
	//		{
	//			keys.add(key);
	//		}
	//		return keys;
	//	}

	private Query<T> buildQueryByExample(T exampleObj)
	{
		Query<T> q = ofy().query(clazz);

		// Add all non-null properties to query filter
		for (Field field : clazz.getDeclaredFields())
		{
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class)
					|| (field.isAnnotationPresent(Embedded.class))
					|| (field.getType().isArray())
					|| (Collection.class.isAssignableFrom(field.getType()))
					|| ((field.getModifiers() & BAD_MODIFIERS) != 0))
				continue;

			field.setAccessible(true);

			Object value;
			try
			{
				value = field.get(exampleObj);
			}
			catch (IllegalArgumentException e)
			{
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
			if (value != null)
			{
				q.filter(field.getName(), value);
			}
		}

		return q;
	}
}
