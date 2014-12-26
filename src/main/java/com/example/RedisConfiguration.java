package com.example;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisConfiguration
{
	@NotEmpty
	private String hostname;

	@Min(1)
    @Max(65535)
	private Integer port;

	@JsonProperty
	public String getHostname()
	{
		return hostname;
	}
	@JsonProperty
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
	@JsonProperty
	public Integer getPort()
	{
		return port;
	}
	@JsonProperty
	public void setPort(Integer port)
	{
		this.port = port;
	}

}
