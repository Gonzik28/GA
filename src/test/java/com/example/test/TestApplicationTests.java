package com.example.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TestApplicationTests {
	@Mock
	private StatisticService statisticService;
	private static Statistic statisticBefore;

	@BeforeAll
	static void setUp() {
		statisticBefore = new Statistic();
		statisticBefore.setIncrement(1l);
		statisticBefore.setSum(1l);
	}

	@Test
	void testCreate() {
		StatisticService statisticServiceSpy = Mockito.spy(statisticService);
		Mockito.doReturn(statisticBefore).when(statisticServiceSpy).create(1L);
		Statistic statistic = statisticServiceSpy.create(1L);
		assertEquals(1L, statistic.getSum());
		assertEquals(1L, statistic.getIncrement());
		Mockito.verify(statisticServiceSpy).create(1L);
	}

	@Test
	void testGetSum() {
		StatisticService statisticServiceSpy = Mockito.spy(statisticService);
		Mockito.doReturn(statisticBefore).when(statisticServiceSpy).create(1L);
		Statistic statisticAfter = new Statistic();
		statisticAfter.setIncrement(2l);
		statisticAfter.setSum(3l);
		Mockito.doReturn(statisticAfter).when(statisticServiceSpy).create(2L);
		assertEquals(3L, statisticServiceSpy.create(2L).getSum());
		Mockito.verify(statisticServiceSpy, Mockito.times(2));
	}

	@Test
	void parser() {
		assertEquals(1L, MainView.parser("1"));
	}

	@Test
	void parserThrowsExceptionWhenInputIsNotANumber() {
		assertThrows(RuntimeException.class, () -> MainView.parser("abc"));
		assertThrows(RuntimeException.class, () -> MainView.parser("2.4"));
		assertThrows(RuntimeException.class, () -> MainView.parser("2,4"));
	}

}
