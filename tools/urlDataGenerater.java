/**
 * Created by D on 2016/12/26.
 */
public class urlDataGenerater {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("");
        String[] keyName = {"username","password","fin_id"};

        for(int i=0;i<keyName.length;i++){
            if(i == 0){
                sb.append("\"").append(keyName[i]).append("=\"").append(" & ssssss & \";");
            }else{
                sb.append(keyName[i]).append("=\"").append(" & ssssss & \";");
            }
        }
        sb.append(")\"");
        System.out.println(sb.toString());
    }
}
