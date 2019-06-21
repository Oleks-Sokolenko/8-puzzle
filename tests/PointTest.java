import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest{
	Point p;
	@BeforeEach
	void createPoint(){
		p = new Point(2,3);
	}

	@AfterEach
	void destruct(){
		p = null;
	}

	@Test
	void checkPoint(){
		assertEquals(2, p.x);
		assertEquals(3, p.y);
	}

	@Test
	void getY(){

		assertEquals(3, p.getY());
	}

	@Test
	void getX(){
		assertEquals(2,p.getX());
	}
}