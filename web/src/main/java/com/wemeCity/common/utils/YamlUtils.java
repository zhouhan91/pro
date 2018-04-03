package com.wemeCity.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class YamlUtils {

	@Autowired
	private ApplicationContext ac;

	private Map<String, Object> cnf;

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Class<T> clazz) {
		String[] arrKey = key.split("\\.");
		Map<String, Object> temp = new HashMap<>(cnf);
		for (int i = 0; i < arrKey.length; i++) {
			Object o = temp.get(arrKey[i]);
			if (i != arrKey.length - 1) {
				temp = new HashMap<>((Map<String, Object>) o);
			} else {
				return (T) o;
			}
		}
		return null;
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		cnf = ac.getBean("yamlMap", Map.class);
	}
}
