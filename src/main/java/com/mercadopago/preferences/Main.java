package com.mercadopago.preferences;

import com.mercadopago.preferences.route.Routes;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

	public static void main(String[] args) throws Exception {
		Spark.port(8080);

		new Routes().init();

	}

}
