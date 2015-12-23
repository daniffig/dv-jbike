package com.jbike.persistence;

public class FactoryDao {
	private static BikeDaoHibernate BikeDao = new BikeDaoHibernate();
	
	private static PenalizationDaoHibernate PenalizationDao = new PenalizationDaoHibernate();
	
	private static StationDaoHibernate StationDao = new StationDaoHibernate();
	
	private static UserDaoHibernate UserDao = new UserDaoHibernate();
	
	private static MovementDaoHibernate MovementDao = new MovementDaoHibernate();
	
	public static BikeDaoHibernate getBikeDao(){
		return BikeDao;
	}
	
	public static PenalizationDaoHibernate getPenalizationDao(){
		return PenalizationDao;
	}
	
	public static StationDaoHibernate getStationDao(){
		return StationDao;
	}
	
	public static UserDaoHibernate getUserDao(){
		return UserDao;
	}
	
	public static MovementDaoHibernate getMovementDao(){
		return MovementDao;
	}
}
