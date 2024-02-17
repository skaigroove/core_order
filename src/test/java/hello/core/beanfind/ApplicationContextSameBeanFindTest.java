package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class); // 스프링 컨테이너 생성
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입의 빈이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,  // assertThrows : 특정 예외가 발생하면 테스트가 성공함
                ()->ac.getBean(MemberRepository.class)); // 람다식으로 에러가 발생하는지 확인하는 메서드를 작성
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 저장하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class); // memberRepository1 객체를 컨테이너를 통해 빈을 가져오고 그것을 memberRepository 객체에 저장
        assertThat(memberRepository).isInstanceOf(MemberRepository.class); // memberRepository 객체 타입이 MemberRepository 클래스와 일치하는지 검사
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeansType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class); // Beans 들의 타입을 모두 받아와 맵으로 저장
        for (String key : beansOfType.keySet() ) { // Beans들을 모두 출력
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            System.out.println("beansOfType = " + beansOfType);
            Assertions.assertThat(beansOfType.size()).isEqualTo(2); // beans의 Size가 2가 맞는지 검증

        }
    }

    /**
     * 내부 클래스에서 스프링 설정하여 2개의 빈을 등록. return 값으로는 둘 다 MemoryMemberReposiory를 넘겨줌.
     */
    @Configuration
    static class SameBeanConfig {
        
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}

