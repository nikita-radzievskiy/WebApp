package com.radz.webapp.db.utils;

public class DBConstants {
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";


    public static final String SQL_FIND_ALL_USERS = "SELECT * FROM user u" +
            " INNER JOIN roles r ON u.role_id = r.id";

    public static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD =
            SQL_FIND_ALL_USERS + " WHERE u.login=? && u.password=?";

    public static final String SQL_FIND_USER_BY_LOGIN =
            SQL_FIND_ALL_USERS + " WHERE u.login=?";


    public static final String SQL_CREATE_GOODS = "INSERT INTO goods VALUES (default, ?, ?, CURRENT_TIME,?, ?, ?, ?)";
    public static final String SQL_EDIT_GOODS = "UPDATE goods SET name= ?, price =?," +
            "createdAt=CURRENT_TIME,color =?, size =?,available = ?, category_id=? WHERE id = ?";
    public static final String SQL_FIND_ALL_GOODS = "SELECT * FROM goods g INNER JOIN category c ON g.category_id = c.id";

    public static final String SQL_FIND_GOODS_BY_ID = SQL_FIND_ALL_GOODS + " WHERE g.id=?";

    public static final String SQL_FIND_ALL_GOODS_BY_NAME_AZ = "SELECT * FROM goods g INNER JOIN category c ON" +
            " g.category_id = c.id order by g.name ";

    public static final String SQL_FIND_ALL_GOODS_BY_NAME_ZA = "SELECT * FROM goods g INNER JOIN category c ON" +
            " g.category_id = c.id order by g.name DESC";

    public static final String SQL_FIND_ALL_GOODS_BY_PRICE_HIGH_TO_LOW = "SELECT * FROM goods g INNER JOIN" +
            " category c ON g.category_id = c.id order by g.price DESC";

    public static final String SQL_FIND_ALL_GOODS_BY_PRICE_LOW_TO_HIGH = "SELECT * FROM goods g INNER JOIN" +
            " category c ON g.category_id = c.id order by g.price";

    public static final String SQL_FIND_ALL_GOODS_BY_DATE_OLD_TO_NEW = "SELECT * FROM goods g INNER JOIN category" +
            " c ON g.category_id = c.id order by g.createdAt";

    public static final String SQL_FIND_ALL_GOODS_BY_DATE_NEW_TO_OLD = "SELECT * FROM goods g INNER JOIN category" +
            " c ON g.category_id = c.id order by g.createdAt desc";

    public static final String SQL_FIND_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name_category=?";

    public static final String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";

    public static final String SQL_DELETE_GOODS_BY_ID = "DELETE FROM goods WHERE id=?";


    public static final String SQL_EDIT_USER_ROLE = "UPDATE roles\n" +
            "INNER JOIN user ON user.role_id = roles.id\n" +
            "SET user.role_id = ? \n" +
            "WHERE user.login = ?;";

    public static final String SQL_FIND_ROLE_BY_NAME = "SELECT * FROM roles WHERE name=?";


    public static final String ROLE = "name";


    public static final String GOODS_ID = "id";
    public static final String GOODS_NAME = "name";
    public static final String GOODS_PRICE = "price";
    public static final String GOODS_DATE = "createdAt";
    public static final String GOODS_COLOR = "color";
    public static final String GOODS_SIZE = "size";
    public static final String GOODS_AVAILABLE = "available";
    public static final String GOODS_CATEGORY = "name_category";


    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name_category";


    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";


    public static final String STATUS_ID = "id";
    public static final String STATUS_NAME = "name";
    public static final String SQL_FIND_STATUS_BY_ID = "SELECT * FROM statuses WHERE id=?";
    public static final String SQL_FIND_STATUS_BY_NAME = "SELECT * FROM statuses WHERE name=?";
    public static final String SQL_CREATE_ORDER = "INSERT INTO orders VALUES (DEFAULT, ?, ?, CURRENT_TIME)";
    public static final String SQL_ADD_GOODS_TO_ORDER = "INSERT INTO orders_goods VALUES (?, ?)";


    public static final String SQL_FIND_ORDER_BY_LOGIN = "select * from orders where user_id=?";

    public static final String ORDER_ID = "id";
    public static final String SQL_FIND_ALL_GOODS_BY_ORDER_ID = "select  g.id,g.name,g.price,g.createdAt, " +
            "g.color,g.size,g.available, name_category, statuses.name from goods g  inner join orders_goods on " +
            "g.id= orders_goods.goods_id  inner join orders on orders.id = orders_goods.order_id inner join " +
            "category on category.id = g.category_id inner join statuses on statuses.id=orders.status_id where" +
            " orders.id=? order by g.name";
    public static final String SQL_EDIT_ORDER_STATUS_BY_ID = "UPDATE orders set orders.status_id=?  WHERE orders.id =?";
    public static final String SQL_FIND_ALL_GOODS_BY_RANGE_PRICE = "SELECT * FROM goods inner join category on" +
            " goods.category_id=category.id WHERE goods.price>=? and goods.price<=?";
    public static final String SQL_FIND_ALL_GOODS_BY_CATEGORY = "SELECT * FROM goods inner join " +
            "category on goods.category_id=category.id WHERE category.name_category=?";
    public static final String SQL_FIND_ALL_GOODS_BY_COLOR = "SELECT * FROM goods inner join " +
            "category on goods.category_id=category.id WHERE goods.color=?";
    public static final String SQL_FIND_ALL_GOODS_BY_RANGE_SIZE = "SELECT * FROM goods inner join category on" +
            " goods.category_id=category.id WHERE goods.size>=? and goods.size<=?";


    public static final String SQL_FIND_ALL_ORDER = "select * from orders order by orders.status_id";

}
