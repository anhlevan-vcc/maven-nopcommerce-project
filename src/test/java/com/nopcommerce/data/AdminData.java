package com.nopcommerce.data;

import java.util.Locale;

import com.github.javafaker.Faker;

import commons.BaseTest;

public class AdminData extends BaseTest {
	private static Locale local = new Locale("en");
	private static Faker faker = new Faker(local);

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
		public static final String EMAIL = faker.internet().emailAddress();
		public static final String PASSWORD = "123456789";
		public static final String FIRST_NAME = faker.name().firstName();
		public static final String LAST_NAME = faker.name().lastName();
		public static final String GENDER = "Male";
		public static final String DATE_OF_BIRTH = "4/3/1990";
		public static final String DAY = "3";
		public static final String MONTH = "4";
		public static final String COMPANY = faker.company().name();
		public static final String CUSTOMER_ROLES = "Guests";
		public static final String ADMIN_COMMENT = "ADD NEW CUSTOMER";
		public static final String CUSTOMER_ROLES_RESGISTER = "Registered";
		public static final String MESSAGE_SUCCESS = "The new customer has been added successfully.";
	}

	public static class CustomerEditInfo {
		public static final String EMAIL = faker.internet().emailAddress();
		public static final String PASSWORD = "123456789";
		public static final String FIRST_NAME = faker.name().firstName();
		public static final String LAST_NAME = faker.name().lastName();
		public static final String GENDER = "Male";
		public static final String DATE_OF_BIRTH = "7/7/1990";
		public static final String DAY = "7";
		public static final String MONTH = "7";
		public static final String COMPANY = faker.company().name();
		public static final String CUSTOMER_ROLES = "Guests";
		public static final String ADMIN_COMMENT = "EDIT ADD NEW CUSTOMER";
		public static final String CUSTOMER_ROLES_RESGISTER = "Registered";
		public static final String MESSAGE_SUCCESS = "The customer has been updated successfully.";
	}
}
