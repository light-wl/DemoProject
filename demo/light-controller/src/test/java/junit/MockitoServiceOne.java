package junit;

public class MockitoServiceOne {

    private MockitoServiceTwo mockitoServiceTwo;

    public MockitoServiceOne() {
    }

    public MockitoServiceOne(MockitoServiceTwo mockitoServiceTwo) {
        this.mockitoServiceTwo = mockitoServiceTwo;
    }

    public int getDemoStatus() {
        return mockitoServiceTwo.getDemoStatus();
    }

    public int add(int a, int b) {
        return a + b;
    }
}

