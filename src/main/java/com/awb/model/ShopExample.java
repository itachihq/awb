package com.awb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNull() {
            addCriterion("shop_price is null");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNotNull() {
            addCriterion("shop_price is not null");
            return (Criteria) this;
        }

        public Criteria andShopPriceEqualTo(String value) {
            addCriterion("shop_price =", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotEqualTo(String value) {
            addCriterion("shop_price <>", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThan(String value) {
            addCriterion("shop_price >", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThanOrEqualTo(String value) {
            addCriterion("shop_price >=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThan(String value) {
            addCriterion("shop_price <", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThanOrEqualTo(String value) {
            addCriterion("shop_price <=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLike(String value) {
            addCriterion("shop_price like", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotLike(String value) {
            addCriterion("shop_price not like", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceIn(List<String> values) {
            addCriterion("shop_price in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotIn(List<String> values) {
            addCriterion("shop_price not in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceBetween(String value1, String value2) {
            addCriterion("shop_price between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotBetween(String value1, String value2) {
            addCriterion("shop_price not between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPictureIsNull() {
            addCriterion("shop_picture is null");
            return (Criteria) this;
        }

        public Criteria andShopPictureIsNotNull() {
            addCriterion("shop_picture is not null");
            return (Criteria) this;
        }

        public Criteria andShopPictureEqualTo(String value) {
            addCriterion("shop_picture =", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureNotEqualTo(String value) {
            addCriterion("shop_picture <>", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureGreaterThan(String value) {
            addCriterion("shop_picture >", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureGreaterThanOrEqualTo(String value) {
            addCriterion("shop_picture >=", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureLessThan(String value) {
            addCriterion("shop_picture <", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureLessThanOrEqualTo(String value) {
            addCriterion("shop_picture <=", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureLike(String value) {
            addCriterion("shop_picture like", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureNotLike(String value) {
            addCriterion("shop_picture not like", value, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureIn(List<String> values) {
            addCriterion("shop_picture in", values, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureNotIn(List<String> values) {
            addCriterion("shop_picture not in", values, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureBetween(String value1, String value2) {
            addCriterion("shop_picture between", value1, value2, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andShopPictureNotBetween(String value1, String value2) {
            addCriterion("shop_picture not between", value1, value2, "shopPicture");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andInformationIsNull() {
            addCriterion("information is null");
            return (Criteria) this;
        }

        public Criteria andInformationIsNotNull() {
            addCriterion("information is not null");
            return (Criteria) this;
        }

        public Criteria andInformationEqualTo(String value) {
            addCriterion("information =", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotEqualTo(String value) {
            addCriterion("information <>", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationGreaterThan(String value) {
            addCriterion("information >", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationGreaterThanOrEqualTo(String value) {
            addCriterion("information >=", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLessThan(String value) {
            addCriterion("information <", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLessThanOrEqualTo(String value) {
            addCriterion("information <=", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLike(String value) {
            addCriterion("information like", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotLike(String value) {
            addCriterion("information not like", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationIn(List<String> values) {
            addCriterion("information in", values, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotIn(List<String> values) {
            addCriterion("information not in", values, "information");
            return (Criteria) this;
        }

        public Criteria andInformationBetween(String value1, String value2) {
            addCriterion("information between", value1, value2, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotBetween(String value1, String value2) {
            addCriterion("information not between", value1, value2, "information");
            return (Criteria) this;
        }

        public Criteria andDetaiIsNull() {
            addCriterion("detai is null");
            return (Criteria) this;
        }

        public Criteria andDetaiIsNotNull() {
            addCriterion("detai is not null");
            return (Criteria) this;
        }

        public Criteria andDetaiEqualTo(String value) {
            addCriterion("detai =", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiNotEqualTo(String value) {
            addCriterion("detai <>", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiGreaterThan(String value) {
            addCriterion("detai >", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiGreaterThanOrEqualTo(String value) {
            addCriterion("detai >=", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiLessThan(String value) {
            addCriterion("detai <", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiLessThanOrEqualTo(String value) {
            addCriterion("detai <=", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiLike(String value) {
            addCriterion("detai like", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiNotLike(String value) {
            addCriterion("detai not like", value, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiIn(List<String> values) {
            addCriterion("detai in", values, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiNotIn(List<String> values) {
            addCriterion("detai not in", values, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiBetween(String value1, String value2) {
            addCriterion("detai between", value1, value2, "detai");
            return (Criteria) this;
        }

        public Criteria andDetaiNotBetween(String value1, String value2) {
            addCriterion("detai not between", value1, value2, "detai");
            return (Criteria) this;
        }

        public Criteria andStandardIsNull() {
            addCriterion("standard is null");
            return (Criteria) this;
        }

        public Criteria andStandardIsNotNull() {
            addCriterion("standard is not null");
            return (Criteria) this;
        }

        public Criteria andStandardEqualTo(String value) {
            addCriterion("standard =", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotEqualTo(String value) {
            addCriterion("standard <>", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThan(String value) {
            addCriterion("standard >", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThanOrEqualTo(String value) {
            addCriterion("standard >=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThan(String value) {
            addCriterion("standard <", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThanOrEqualTo(String value) {
            addCriterion("standard <=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLike(String value) {
            addCriterion("standard like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotLike(String value) {
            addCriterion("standard not like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardIn(List<String> values) {
            addCriterion("standard in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotIn(List<String> values) {
            addCriterion("standard not in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardBetween(String value1, String value2) {
            addCriterion("standard between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotBetween(String value1, String value2) {
            addCriterion("standard not between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andIssellingIsNull() {
            addCriterion("isselling is null");
            return (Criteria) this;
        }

        public Criteria andIssellingIsNotNull() {
            addCriterion("isselling is not null");
            return (Criteria) this;
        }

        public Criteria andIssellingEqualTo(Integer value) {
            addCriterion("isselling =", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingNotEqualTo(Integer value) {
            addCriterion("isselling <>", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingGreaterThan(Integer value) {
            addCriterion("isselling >", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingGreaterThanOrEqualTo(Integer value) {
            addCriterion("isselling >=", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingLessThan(Integer value) {
            addCriterion("isselling <", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingLessThanOrEqualTo(Integer value) {
            addCriterion("isselling <=", value, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingIn(List<Integer> values) {
            addCriterion("isselling in", values, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingNotIn(List<Integer> values) {
            addCriterion("isselling not in", values, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingBetween(Integer value1, Integer value2) {
            addCriterion("isselling between", value1, value2, "isselling");
            return (Criteria) this;
        }

        public Criteria andIssellingNotBetween(Integer value1, Integer value2) {
            addCriterion("isselling not between", value1, value2, "isselling");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyIsNull() {
            addCriterion("isdirectly is null");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyIsNotNull() {
            addCriterion("isdirectly is not null");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyEqualTo(Integer value) {
            addCriterion("isdirectly =", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyNotEqualTo(Integer value) {
            addCriterion("isdirectly <>", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyGreaterThan(Integer value) {
            addCriterion("isdirectly >", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdirectly >=", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyLessThan(Integer value) {
            addCriterion("isdirectly <", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyLessThanOrEqualTo(Integer value) {
            addCriterion("isdirectly <=", value, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyIn(List<Integer> values) {
            addCriterion("isdirectly in", values, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyNotIn(List<Integer> values) {
            addCriterion("isdirectly not in", values, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyBetween(Integer value1, Integer value2) {
            addCriterion("isdirectly between", value1, value2, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsdirectlyNotBetween(Integer value1, Integer value2) {
            addCriterion("isdirectly not between", value1, value2, "isdirectly");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNull() {
            addCriterion("isnew is null");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNotNull() {
            addCriterion("isnew is not null");
            return (Criteria) this;
        }

        public Criteria andIsnewEqualTo(Integer value) {
            addCriterion("isnew =", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotEqualTo(Integer value) {
            addCriterion("isnew <>", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThan(Integer value) {
            addCriterion("isnew >", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThanOrEqualTo(Integer value) {
            addCriterion("isnew >=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThan(Integer value) {
            addCriterion("isnew <", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThanOrEqualTo(Integer value) {
            addCriterion("isnew <=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewIn(List<Integer> values) {
            addCriterion("isnew in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotIn(List<Integer> values) {
            addCriterion("isnew not in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewBetween(Integer value1, Integer value2) {
            addCriterion("isnew between", value1, value2, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotBetween(Integer value1, Integer value2) {
            addCriterion("isnew not between", value1, value2, "isnew");
            return (Criteria) this;
        }

        public Criteria andOrdIsNull() {
            addCriterion("ord is null");
            return (Criteria) this;
        }

        public Criteria andOrdIsNotNull() {
            addCriterion("ord is not null");
            return (Criteria) this;
        }

        public Criteria andOrdEqualTo(Integer value) {
            addCriterion("ord =", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdNotEqualTo(Integer value) {
            addCriterion("ord <>", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdGreaterThan(Integer value) {
            addCriterion("ord >", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ord >=", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdLessThan(Integer value) {
            addCriterion("ord <", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdLessThanOrEqualTo(Integer value) {
            addCriterion("ord <=", value, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdIn(List<Integer> values) {
            addCriterion("ord in", values, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdNotIn(List<Integer> values) {
            addCriterion("ord not in", values, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdBetween(Integer value1, Integer value2) {
            addCriterion("ord between", value1, value2, "ord");
            return (Criteria) this;
        }

        public Criteria andOrdNotBetween(Integer value1, Integer value2) {
            addCriterion("ord not between", value1, value2, "ord");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(Integer value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(Integer value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(Integer value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(Integer value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(Integer value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<Integer> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<Integer> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(Integer value1, Integer value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(Integer value1, Integer value2) {
            addCriterion("sign not between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andIspreferentialIsNull() {
            addCriterion("ispreferential is null");
            return (Criteria) this;
        }

        public Criteria andIspreferentialIsNotNull() {
            addCriterion("ispreferential is not null");
            return (Criteria) this;
        }

        public Criteria andIspreferentialEqualTo(Integer value) {
            addCriterion("ispreferential =", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialNotEqualTo(Integer value) {
            addCriterion("ispreferential <>", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialGreaterThan(Integer value) {
            addCriterion("ispreferential >", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialGreaterThanOrEqualTo(Integer value) {
            addCriterion("ispreferential >=", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialLessThan(Integer value) {
            addCriterion("ispreferential <", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialLessThanOrEqualTo(Integer value) {
            addCriterion("ispreferential <=", value, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialIn(List<Integer> values) {
            addCriterion("ispreferential in", values, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialNotIn(List<Integer> values) {
            addCriterion("ispreferential not in", values, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialBetween(Integer value1, Integer value2) {
            addCriterion("ispreferential between", value1, value2, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andIspreferentialNotBetween(Integer value1, Integer value2) {
            addCriterion("ispreferential not between", value1, value2, "ispreferential");
            return (Criteria) this;
        }

        public Criteria andPostageIsNull() {
            addCriterion("postage is null");
            return (Criteria) this;
        }

        public Criteria andPostageIsNotNull() {
            addCriterion("postage is not null");
            return (Criteria) this;
        }

        public Criteria andPostageEqualTo(String value) {
            addCriterion("postage =", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotEqualTo(String value) {
            addCriterion("postage <>", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageGreaterThan(String value) {
            addCriterion("postage >", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageGreaterThanOrEqualTo(String value) {
            addCriterion("postage >=", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageLessThan(String value) {
            addCriterion("postage <", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageLessThanOrEqualTo(String value) {
            addCriterion("postage <=", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageLike(String value) {
            addCriterion("postage like", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotLike(String value) {
            addCriterion("postage not like", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageIn(List<String> values) {
            addCriterion("postage in", values, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotIn(List<String> values) {
            addCriterion("postage not in", values, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageBetween(String value1, String value2) {
            addCriterion("postage between", value1, value2, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotBetween(String value1, String value2) {
            addCriterion("postage not between", value1, value2, "postage");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andAdmnumIsNull() {
            addCriterion("admnum is null");
            return (Criteria) this;
        }

        public Criteria andAdmnumIsNotNull() {
            addCriterion("admnum is not null");
            return (Criteria) this;
        }

        public Criteria andAdmnumEqualTo(Integer value) {
            addCriterion("admnum =", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumNotEqualTo(Integer value) {
            addCriterion("admnum <>", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumGreaterThan(Integer value) {
            addCriterion("admnum >", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("admnum >=", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumLessThan(Integer value) {
            addCriterion("admnum <", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumLessThanOrEqualTo(Integer value) {
            addCriterion("admnum <=", value, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumIn(List<Integer> values) {
            addCriterion("admnum in", values, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumNotIn(List<Integer> values) {
            addCriterion("admnum not in", values, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumBetween(Integer value1, Integer value2) {
            addCriterion("admnum between", value1, value2, "admnum");
            return (Criteria) this;
        }

        public Criteria andAdmnumNotBetween(Integer value1, Integer value2) {
            addCriterion("admnum not between", value1, value2, "admnum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}