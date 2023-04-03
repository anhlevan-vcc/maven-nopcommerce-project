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

	public static class AddNewAddress {
		public static final String FIRST_NAME = faker.name().firstName();
		public static final String LAST_NAME = faker.name().lastName();
		public static final String EMAIL = faker.internet().emailAddress();
		public static final String COMPANY = faker.company().name();
		public static final String COUNTRY = "Viet Nam";
		public static final String STALE = "Other";
		public static final String CITY = faker.address().city();
		public static final String ADD1 = faker.address().fullAddress();
		public static final String ADD2 = faker.address().fullAddress();
		public static final String ZIP = faker.address().zipCode();
		public static final String PHONE_NUMBER = faker.phoneNumber().phoneNumber();
		public static final String FAX_NUMBER = faker.phoneNumber().phoneNumber();
		public static final String MESSAGE_SUCCESS = "The new address has been added successfully.";
		public static final String ADDRESS_IN_TABLE = "Kuvalis-MacGyver" + "\nApt. 206 5074 Flatley Trail, East Brendon, CA 28049-8604"
				+ "\n4313 Welch Walk, Meaganhaven, VT 57481-1967" + "\nWest Eliaside,48555" + "\nViet Nam";
	}
}
