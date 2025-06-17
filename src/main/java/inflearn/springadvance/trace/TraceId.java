package inflearn.springadvance.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0,8);
    }

    // 로그 ID는 똑같고, Level을 한 개 증가시켜서 구분되도록 하는 메서드
    /**
     * 87b63be1 [log1]
     * 87b63be1 |------> [log2]
     */
    public TraceId createNextLevel() {
        return new TraceId(id, level + 1);
    }

    // 하위 레벨에서 return 되면 상위 레벨로 다시 복귀하는 과정
    public TraceId createPreviousLevel(){
        return new TraceId(id, level -1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
