package org.nightdivers.kupica.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.nightdivers.kupica.support.config.TestSecurityConfig;
import org.springframework.context.annotation.Import;

@BaseTestEnvironment
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TestSecurityConfig.class)
public @interface ControllerTest {

}
