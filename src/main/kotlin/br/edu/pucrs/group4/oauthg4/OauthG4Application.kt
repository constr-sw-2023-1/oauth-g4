package br.edu.pucrs.group4.oauthg4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class OauthG4Application

fun main(args: Array<String>) {
	runApplication<OauthG4Application>(*args)
}
