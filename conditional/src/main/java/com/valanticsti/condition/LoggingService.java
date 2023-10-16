package com.valanticsti.condition;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(Java17Condition.class) // custom condition
@ConditionalOnProperty(
        value = "logging.enabled",
        havingValue = "true",
        matchIfMissing = true
)
@ConditionalOnExpression(
        "${logging.enabled:true} and '${logging.leve}'.equals('DEBUG')" // expression evaluates to true
)
@ConditionalOnBean(Java17DependentService.class) // given bean was created
@ConditionalOnMissingBean(Java17DependentService.class) // given bean was not created
@ConditionalOnClass(Java17DependentService.class) // given class exists on classpath
@ConditionalOnMissingClass(value = "com.valantic.sti.Java17DependentService") // given class exists on classpath
@ConditionalOnResource(resources = "/logback.xml") // load only if resource is available
@ConditionalOnJava(JavaVersion.SEVENTEEN) // runtime has to be Java 17
public class LoggingService {
}
