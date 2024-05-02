package org.nightdivers.kupica.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

@ActiveProfiles("test")
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface BaseTestEnvironment {

}
