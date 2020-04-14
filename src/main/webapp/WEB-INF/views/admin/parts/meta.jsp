<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>   
<!DOCTYPE html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
  
<myTag:set var="REQUESTED" value="${SellerServiceImpl.STATUS_REQUESTED}"/>
<myTag:set var="APPROVED" value="${SellerServiceImpl.STATUS_APPROVED}"/>
<myTag:set var="STOPPED" value="${SellerServiceImpl.STATUS_STOPPED}"/>
