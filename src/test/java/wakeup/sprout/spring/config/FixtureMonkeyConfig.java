package wakeup.sprout.spring.config;

import com.navercorp.fixturemonkey.FixtureMonkey;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FixtureMonkeyConfig {
    @Bean
    public FixtureMonkey fixtureMonkey() {
        return FixtureMonkey.builder()
                .defaultNotNull(true)
                .build();
    }
}
