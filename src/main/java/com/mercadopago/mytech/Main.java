package com.mercadopago.mytech;

import com.mercadopago.mytech.route.Routes;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

	public static void main(String[] args) throws Exception {
		Spark.port(8080);

		new Routes().init();

	}

}
