package com.light.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class ServiceTwo {
}
