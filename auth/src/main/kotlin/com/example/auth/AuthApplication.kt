package com.example.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication(scanBasePackages = ["com.example.auth", "luckytree.poom.core"])
class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}
