package com.nopcommerce.data;

import commons.BaseTest;

public class AdminData extends BaseTest {
	public static class Login {
		public static final String EMAIL = "admin@yourstore.com";
		public static final String PASSWORD = "admin";
	}

	public static class ProductPage {
		public static final String PRODUCT_NAME = "Apple iCam";
		public static final String SKU = "APPLE_CAM";
		public static final String PRICE = "1300";
		public static final String QUANTITY = "10000";
	}

	public static class CustomerInfo {
		public static final String EMAIL = "leanh" + generateRandomNumber() + "@gmail.com";
		public static final String PASSWORD = "123456789";
		public static final String FIRST_NAME = "Lê";
		public static final String LAST_NAME = "Anh";
		public static final String GENDER = "Male";
		public static final String DATE_OF_BIRTH = "4/3/1990";
		public static final String COMPANY = "VCC";
		public static final String CUSTOMER_ROLES = "Guests";
		public static final String ADMIN_COMMENT = "ADD NEW CUSTOMER";
		public static final String CUSTOMER_ROLES_RESGISTER = "Registered";
		public static final String MESSAGE_SUCCESS = "The new customer has been added successfully.";
	}
}
