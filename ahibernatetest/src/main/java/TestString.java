
/**
 * @description 测试值传递，类中的值交换了后火发生变化么
 *
 * @author zenghua
 *
 * @createtime 2012-11-19 下午09:17:58
 */

public class TestString {
	private String name;

	public static void main(String[] args) {
		TestString t1 = new TestString();
		t1.setName("t1");
		TestString t2 = t1;
		t2.setName("t2");
		System.out.println(t2.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void t(String s) {
		name = s;
	}
}
