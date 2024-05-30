package com.scanner.scanner.model

import java.io.Serializable


class DomainCVE(
    var address: String? = null,
    val openPorts: MutableList<Ports> = mutableListOf()
): Serializable

class Ports(
    val portId: Long? = null,
    val port: String,
    val protocol: String,
    val vulners: MutableList<Vulners> = mutableListOf(),
    val service: Serv,
): Serializable

class Vulners(
    val vulnersId: Long? = null,
    val cvss: String,
    val vulnersName: String,
    val isExploit: String?,
): Serializable


class Serv(
    val serviceId: Long? = null,
    val name: String,
    val product: String,
    val version: String,
): Serializable