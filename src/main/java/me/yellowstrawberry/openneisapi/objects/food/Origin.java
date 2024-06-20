package me.yellowstrawberry.openneisapi.objects.food;

public class Origin {
    private final String name;
    private String from;

    /**
     * <strong>원산지 표기</strong>가져오기
     *
     * 해당하는 이름의 재료의 원산지가 어디있는지 알 수 있습니다.
     *
     * @since 0.0.2
     * */
    public Origin(String text) {
        String[] sts = text.split(" : ");
        this.name = sts[0];
        if(sts.length==2) this.from = sts[1];
    }

    /**
     * 재료의 이름 가져오기
     *
     * @return 재료의 이름
     * @since 0.0.2
     * */
    public String getName() {
        return name;
    }

    /**
     * 재료의 원산지 가져오기
     *
     * @return 원산지 이름
     * @since 0.0.2
     * */
    public String getFrom() {
        return from;
    }
}
