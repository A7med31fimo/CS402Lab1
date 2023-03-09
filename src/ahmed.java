import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ahmed {
    public static String encrypt_by_mono(String p, String key) {
        key = key.toLowerCase();
        String ans = "";
        int tmp = 0;
        for (int i = 0; i < p.length(); i++) {
            if (Character.isAlphabetic(p.charAt(i))) {
                tmp = Character.toLowerCase(p.charAt(i)) - 'a';
                ans += (key.charAt(tmp));
            } else
                ans += " ";
        }
        return ans.toUpperCase();
    }

    public static String decrypt_by_mono(String c, String key) {
        key = key.toLowerCase();
        String ans = "";
        int tmp = 0;
        for (int i = 0; i < c.length(); i++) {
            if (Character.isAlphabetic(c.charAt(i))) {
                tmp = key.indexOf(Character.toLowerCase(c.charAt(i)));
                ans += (char) (tmp + 'a');
            } else
                ans += " ";
        }
        return ans.toUpperCase();
    }

    public static String encrypt_by_playfair_matrix(String p, String key) {
        Map<Character, Boolean> m = new HashMap<Character, Boolean>();
        key = key.toLowerCase();
        for (int i = 0; i < 26; i++) {
            m.put((char) ('a' + i), true);
        }
        String new_key = "";
        for (int i = 0; i < key.length(); i++) {
            if (m.get((key.charAt(i)))) {
                new_key += key.charAt(i);
                m.remove((key.charAt(i)));
            }
        }
        String tmp = "";
        int flg = 0;
        if (m.get('i') && m.get('j')) {
            m.remove('j');
        } else if (m.get('i')) {
            m.remove('i');
        } else if (m.get('j')) {
            m.remove('j');
        } else {
            flg = 1;
        }
        for (Map.Entry<Character, Boolean> me : m.entrySet()) {
            if (flg == 1) {
                flg = 0;
                continue;
            }
            tmp += me.getKey();
        }
//        System.out.println(tmp);
        int len = new_key.length(), x = 0, y = 0;
        String[] matrix = new String[5];
        //built matrix of cipher
        for (int i = 0; i < 5; i++) {
            matrix[i] = "";
            for (int j = 0; j < 5; j++) {
                if (x < len) {
                    matrix[i] += new_key.charAt(x);
                    x++;
                } else {
                    matrix[i] += tmp.charAt(y);
                    y++;
                }
            }

//            System.out.println(matrix[i]+" ");
        }
        String new_p = "";
        x = 0;
        for (int i = 0; i < p.length(); i+=2) {
            if (Character.isAlphabetic(p.charAt(i))) {
                if (i < p.length() - 1 && p.charAt(i) != p.charAt(i + 1)) {
                    new_p += p.charAt(i);
                    new_p += p.charAt(i+1);
                } else {
                    new_p += p.charAt(i);
                    new_p += 'x';
                    i--;
                }
                x++;
            } else {
                if (x % 2 == 0) {
                    new_p += p.charAt(i);
                    new_p += p.charAt(i+1);
                    x = 0;
                } else {
                    new_p += 'x';
                    new_p += p.charAt(i);
                    i--;
                    x = 0;
                }
            }
        }
//        System.out.println(new_p);
        char c1, c2;
        int col1 = 0, row1 = 0, col2 = 0, row2 = 0;
        String ans = "";
        for (int i = 0; i < new_p.length(); i+=2) {
            if (Character.isAlphabetic(new_p.charAt(i))) {
                c1 = new_p.charAt(i);
                c2 = new_p.charAt(i + 1);
                for (int j = 0; j < 5; j++) {
                    if (matrix[j].contains(c1 + "")) {
                        col1 = matrix[j].indexOf(c1);
                        row1 = j;
                    }
                    if (matrix[j].contains(c2 + "")) {
                        col2 = matrix[j].indexOf(c2);
                        row2 = j;
                    }
                }
//                System.out.println(row1+" "+col1+" "+row2+" "+col2);

                if (row1 != row2 && col1 != col2) {
                    ans += matrix[row1].charAt(col2);
                    ans += matrix[row2].charAt(col1);
                } else if (row1 == row2) {
                    ans += matrix[row1].charAt((col1 + 1) % 5);
                    ans += matrix[row2].charAt((col2 + 1) % 5);
                } else {
                    ans += matrix[(row1 + 1) % 5].charAt(col1);
                    ans += matrix[(row2 + 1) % 5].charAt(col2);
                }

            } else {
                ans += new_p.charAt(i);
            }
        }

        return ans;
    }
    public static String decrypt_by_playfair_matrix(String c, String key) {
        Map<Character, Boolean> m = new HashMap<Character, Boolean>();
        key = key.toLowerCase();
        for (int i = 0; i < 26; i++) {
            m.put((char) ('a' + i), true);
        }
        String new_key = "";
        for (int i = 0; i < key.length(); i++) {
            if (m.get((key.charAt(i)))) {
                new_key += key.charAt(i);
                m.remove((key.charAt(i)));
            }
        }
        String tmp = "";
        int flg = 0;
        if (m.get('i') && m.get('j')) {
            m.remove('j');
        } else if (m.get('i')) {
            m.remove('i');
        } else if (m.get('j')) {
            m.remove('j');
        } else {
            flg = 1;
        }
        for (Map.Entry<Character, Boolean> me : m.entrySet()) {
            if (flg == 1) {
                flg = 0;
                continue;
            }
            tmp += me.getKey();
        }
//        System.out.println(tmp);
        int len = new_key.length(), x = 0, y = 0;
        String[] matrix = new String[5];
        //built matrix of cipher
        for (int i = 0; i < 5; i++) {
            matrix[i] = "";
            for (int j = 0; j < 5; j++) {
                if (x < len) {
                    matrix[i] += new_key.charAt(x);
                    x++;
                } else {
                    matrix[i] += tmp.charAt(y);
                    y++;
                }
            }

//            System.out.println(matrix[i]+" ");
        }
        String new_c = "";
        x = 0;
        for (int i = 0; i < c.length(); i+=2) {
            if (Character.isAlphabetic(c.charAt(i))) {
                if (i < c.length() - 1 && c.charAt(i) != c.charAt(i + 1)) {
                    new_c += c.charAt(i);
                    new_c += c.charAt(i+1);
                } else {
                    new_c += c.charAt(i);
                    new_c += 'x';
                    i--;
                }
                x++;
            } else {
                if (x % 2 == 0) {
                    new_c += c.charAt(i);
                    new_c += c.charAt(i+1);
                    x = 0;
                } else {
                    new_c += 'x';
                    new_c += c.charAt(i);
                    i--;
                    x = 0;
                }
            }
        }
//        System.out.println(new_c);
        char c1, c2;
        int col1 = 0, row1 = 0, col2 = 0, row2 = 0;
        String ans = "";
        for (int i = 0; i < new_c.length(); i+=2) {
            if (Character.isAlphabetic(new_c.charAt(i))) {
                c1 = new_c.charAt(i);
                c2 = new_c.charAt(i + 1);
                for (int j = 0; j < 5; j++) {
                    if (matrix[j].contains(c1 + "")) {
                        col1 = matrix[j].indexOf(c1);
                        row1 = j;
                    }
                    if (matrix[j].contains(c2 + "")) {
                        col2 = matrix[j].indexOf(c2);
                        row2 = j;
                    }
                }
//                System.out.println(row1+" "+col1+" "+row2+" "+col2);

                if (row1 != row2 && col1 != col2) {
                    ans += matrix[row1].charAt(col2);
                    ans += matrix[row2].charAt(col1);
                } else if (row1 == row2) {
                    ans += matrix[row1].charAt(((col1 - 1)+5) % 5);
                    ans += matrix[row2].charAt(((col2 - 1)+5) % 5);
                } else {
                    ans += matrix[((row1 - 1)+5) % 5].charAt(col1);
                    ans += matrix[((row2 - 1)+5) % 5].charAt(col2);
                }

            } else {
                ans += new_c.charAt(i);
            }
        }
        for (int i = 1; i < ans.length()-1; i++) {
            if(ans.charAt(i)=='x'&&ans.charAt(i-1)==ans.charAt(i+1))
               ans=ans.substring(0,i)+ans.substring(i+1);
        }
        return ans;
    }
    public static void main(String[] args) {
        String key = "monarchy", p = "balloon";
        String c= encrypt_by_playfair_matrix(p, key);
        System.out.println(decrypt_by_playfair_matrix(c, key));
    }
}