package me.yellowstrawberry.openneisapi.objects.food;

import java.util.ArrayList;
import java.util.List;

/**
 * 알러지(알레르기) 종류
 * @since 0.0.2
 * */
public enum Allergy {
    //난류
    Egg,
    //우유
    Milk,
    //메밀
    Buckwheat,
    //땅콩
    Peanut,
    //대두
    Soybean,
    //밀
    Wheat,
    //고등어
    Mackerel,
    //게
    Crab,
    //새우
    Shrimp,
    //돼지고기
    Pork,
    //복숭아
    Peach,
    //토마토
    Tomato,
    //아황산염
    Sulfite,
    //호두
    Walnut,
    //닭고기
    Chicken,
    //쇠고기
    Beef,
    //오징어
    Squid,
    //조개류(굴,전복,홍합 등)
    Shellfish;

    public static Allergy[] parse(String st) {
        if(st == null) return null;
        List<Allergy> allergies = new ArrayList<>();
        System.out.println(st);
        String[] sts = st.split("\\.");
        for(String st1 : sts) {
            allergies.add(parse(Integer.parseInt(st1)));
        }

        return allergies.toArray(new Allergy[]{});
    }

    public static Allergy parse(int i) {
        return Allergy.values()[i-1];
    }
}
