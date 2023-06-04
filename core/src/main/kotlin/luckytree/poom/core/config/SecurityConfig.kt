package luckytree.poom.core.config

import feign.Request.HttpMethod
import feign.Request.HttpMethod.*
import luckytree.poom.core.filter.InternalKeyFilter
import luckytree.poom.core.filter.InternalKeyManager
import luckytree.poom.core.filter.JwtFilter
import luckytree.poom.core.handler.JwtAccessDeniedHandler
import luckytree.poom.core.handler.JwtAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtConfiguration: JwtConfiguration,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,

    @Value("\${internal.api.secret}")
    private val internalApiSecret: String,
) {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf().disable()
            .cors().and()
            .httpBasic().disable()
            .rememberMe().disable()
            .logout().disable()
            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                it.accessDeniedHandler(jwtAccessDeniedHandler)
            }
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests {
                it.requestMatchers(
                    AntPathRequestMatcher("/v1/auth/**"),
                    AntPathRequestMatcher("/v1/shops/**"),
                    AntPathRequestMatcher("/v1/members/signup"),
                    AntPathRequestMatcher("/v1/members"),
                    AntPathRequestMatcher("/swagger-ui/**"),
                    AntPathRequestMatcher("/swagger-resources/**"),
                    AntPathRequestMatcher("/v3/api-docs/**"),
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                InternalKeyFilter(InternalKeyManager(internalApiSecret)),
                UsernamePasswordAuthenticationFilter::class.java,
            )
            .addFilterBefore(
                JwtFilter(jwtConfiguration),
                InternalKeyFilter::class.java,
            )

        return httpSecurity.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().apply {
            allowedOriginPatterns = listOf("*")
            allowedMethods = listOf(POST, GET, PATCH, PUT, DELETE, OPTIONS).map(HttpMethod::name)
            allowedHeaders = listOf("*")
            allowCredentials = true
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }
    }
}
