package com.example.v7;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsActive implements Condition {
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return true;
	}
}
