import jakarta.xml.bind.DatatypeConverter;

import java.math.BigInteger;
import java.util.*;

public class ahmed extends DataOfSboxs {
    //*********************ENCRYPTION******************************

    public static String enc_Monoalphabetic(String p, String key) {
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
    public static String enc_Hill(String p, String key) {
        if (p.length() % 2 != 0) {
            p += "x";
        }
        p = p.toLowerCase();
        key = key.toLowerCase();
        int[][] matrix = new int[2][2];
        int x = 0;
        for (int i = 0; i < 2; i++) {
            matrix[i][0] = key.charAt(x) - 'a';
            x++;
            matrix[i][1] = key.charAt(x) - 'a';
            x++;
        }
        String ans = "";
        int[][] mat2 = new int[2][1];
        for (int i = 0; i < p.length(); i += 2) {
            mat2[0][0] = p.charAt(i) - 'a';
            mat2[1][0] = p.charAt(i + 1) - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        if (p.length() % 2 != 0) {
            mat2[0][0] = p.charAt(p.length() - 1) - 'a';
            mat2[1][0] = 'x' - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        return ans.toUpperCase();
    }
    public static String enc_DES(String c, String Key) {
        String tmp = "";
        c = hexToBinary(c);
        int[] ipMatrix = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };
        for (int i = 0; i < ipMatrix.length; i++) {
            tmp += c.charAt(ipMatrix[i] - 1);
        }
        c = tmp;
        String L = c.substring(0, 32), R = c.substring(32, 64);
        String[] keys = DES_Genertor_Key(Key);
        for (int i = 0; i < 16; i++) {
            tmp = R;
            R = xorStrings(L, F(R, keys[i]));
            L = tmp;
        }
        tmp = R + L;
        int[] IP_INV = {40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25};
        String ans = "";
        for (int i = 0; i < IP_INV.length; i++) {
            ans += tmp.charAt(IP_INV[i] - 1);
        }
        tmp = "";
        for (int i = 0; i < 8; i++) {
            BigInteger decimalValue = new BigInteger(ans.substring(0, 8), 2);
            String hexString = decimalValue.toString(16);
            tmp += hexString.length() == 1 ? '0' + hexString : hexString;
            ans = ans.substring(8);
        }
        return tmp;
    }
    public static String enc_Ceasr(String p, String key) {
        p = p.toLowerCase();

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            int idx = ((p.charAt(i) - 'a') + Integer.parseInt(key)) % 26;
            ciphertext.append((char) (idx + 'a'));
        }
        return ciphertext.toString();
    }
    public static String enc_Vigenere(String p, String key) {
        String res = "";
        p = p.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0; key.length() != p.length(); i++) {
            key += key.charAt(i);
        }
        for (int i = 0; i < p.length(); i++) {
            if (Character.isAlphabetic(p.charAt(i))) {
                res += (char) (((p.charAt(i) - 'a') + (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += p.charAt(i);
            }
        }

        return res.toUpperCase();
    }
    public static String enc_Autokey(String p, String key) {
        String res = "";
        p = p.toLowerCase();
        key = key.toLowerCase()+p;
        for (int i = 0; i < p.length(); i++) {
            if (Character.isAlphabetic(p.charAt(i))) {
                //plain text= "ahmed fahim"
                //key =        xorand
                res += (char) (((p.charAt(i) - 'a') + (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += p.charAt(i);
            }
        }
        return res.toUpperCase();
    }
    public static String enc_OneTimePad(String p, String key) {
        p = p.toLowerCase(); // Remove non-alphabetic characters and convert to uppercase
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            int idx = p.charAt(i) - 'a';
            int k = key.charAt(i) - 'a';
            int c = idx ^ k;
            char cc = (char) (c + 'a');
            plaintext.append(cc);
        }
        return plaintext.toString();
    }
    public static String enc_RailFence(String p, String kkey) {
        p=p.toLowerCase();
        int key=Integer.parseInt(kkey);
        char[][] rail = new char[key][p.length()];

        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '@');

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < p.length(); i++) {

            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            rail[row][col++] = p.charAt(i);

            if (dirDown)
                row++;
            else
                row--;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++)
            for (int j = 0; j < p.length(); j++)
                if (rail[i][j] != '@')
                    result.append(rail[i][j]);

        return result.toString();
    }
    public static String enc_RowTransposition(String p, String key) {
        p = p.toLowerCase(); // Remove non-alphabetic characters and convert to uppercase
        int cols = key.length();
        int rows = (int) Math.ceil((double) p.length() / cols);
        char[][] matrix = new char[rows][cols];
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (idx < p.length()) {
                    matrix[i][j] = p.charAt(idx);
                    idx++;
                } else {
                    matrix[i][j] = '@';
                }
            }
        }
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);
        StringBuilder res = new StringBuilder();
        for (char k : sortedKey) {
            int j = key.indexOf(k);
            for (int i = 0; i < rows; i++) {
                res.append(matrix[i][j]);
            }
        }
        return res.toString().toUpperCase();
    }

    public static String enc_Vernam(String p, String key) {
        String res = "";
        p = p.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0; i < p.length(); i++) {
            if (Character.isAlphabetic(p.charAt(i))) {
                res += (char) (((p.charAt(i) - 'a') + (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += p.charAt(i);
            }
        }
        return res.toUpperCase();
    }
    public static String enc_Playfair(String p, String key) {
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

//           System.out.println(matrix[i]+" ");
        }
        String new_p = "";
        x = 0;
        for (int i = 0; i < p.length(); i += 2) {
            if (Character.isAlphabetic(p.charAt(i))) {
                if (i < p.length() - 1 && p.charAt(i) != p.charAt(i + 1)) {
                    new_p += p.charAt(i);
                    new_p += p.charAt(i + 1);
                } else {
                    new_p += p.charAt(i);
                    new_p += 'x';
                    i--;
                }
                x++;
            } else {
                if (x % 2 == 0) {
                    new_p += p.charAt(i);
                    new_p += p.charAt(i + 1);
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
        for (int i = 0; i < new_p.length(); i += 2) {
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





    //*********************DESCRIPTION******************************
    public static String dec_Monoalphabetic(String c, String key) {
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
    public static String dec_Playfair(String c, String key) {
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
        for (int i = 0; i < c.length(); i += 2) {
            if (Character.isAlphabetic(c.charAt(i))) {
                if (i < c.length() - 1 && c.charAt(i) != c.charAt(i + 1)) {
                    new_c += c.charAt(i);
                    new_c += c.charAt(i + 1);
                } else {
                    new_c += c.charAt(i);
                    new_c += 'x';
                    i--;
                }
                x++;
            } else {
                if (x % 2 == 0) {
                    new_c += c.charAt(i);
                    new_c += c.charAt(i + 1);
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
        for (int i = 0; i < new_c.length(); i += 2) {
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
                    ans += matrix[row1].charAt(((col1 - 1) + 5) % 5);
                    ans += matrix[row2].charAt(((col2 - 1) + 5) % 5);
                } else {
                    ans += matrix[((row1 - 1) + 5) % 5].charAt(col1);
                    ans += matrix[((row2 - 1) + 5) % 5].charAt(col2);
                }

            } else {
                ans += new_c.charAt(i);
            }
        }
        for (int i = 1; i < ans.length() - 1; i++) {
            if (ans.charAt(i) == 'x' && ans.charAt(i - 1) == ans.charAt(i + 1))
                ans = ans.substring(0, i) + ans.substring(i + 1);
            if (ans.charAt(i + 1) == 'x' && i + 1 == ans.length() - 1)
                ans = ans.substring(0, i + 1) + ans.substring(i + 2);

        }
        return ans;
    }
    public static String dec_Hill(String c, String key) {
        key = key.toLowerCase();
        int[][] matrix = new int[2][2];

        matrix[0][0] = key.charAt(0) - 'a';
        matrix[0][1] = (key.charAt(1) - 'a') * -1;
        matrix[1][0] = (key.charAt(2) - 'a') * -1;
        matrix[1][1] = (key.charAt(3) - 'a');
        int tmp = matrix[0][0];
        matrix[0][0] = matrix[1][1];
        matrix[1][1] = tmp;
        int determint = (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        determint = make_num_pos(determint);
        if (determint == 0) {
            return "ERROR . . . . . .!";
        }
        int inverse = Inverse_of_num(determint);
        if (inverse == -1) {
            return "ERROR . . . . . .!";
        }

        for (int i = 0; i < 2; i++) {
            matrix[i][0] = make_num_pos(matrix[i][0] * inverse) % 26;
            matrix[i][1] = make_num_pos(matrix[i][1] * inverse) % 26;
        }
        String ans = "";
        int[][] mat2 = new int[2][1];
        for (int i = 0; i < c.length(); i += 2) {
            mat2[0][0] = c.charAt(i) - 'a';
            mat2[1][0] = c.charAt(i + 1) - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        if (ans.charAt(ans.length() - 1) == 'x') {
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans.toUpperCase();
    }
    public static String dec_DES(String c, String Key) {
        String tmp = "";
        c = hexToBinary(c);
        int[] ipMatrix = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };
        for (int i = 0; i < ipMatrix.length; i++) {
            tmp += c.charAt(ipMatrix[i] - 1);
        }
        c = tmp;
        String L = c.substring(0, 32), R = c.substring(32, 64);
        String[] keys = DES_Genertor_Key(Key);
        for (int i = 15; i >= 0; i--) {
            tmp = R;
            R = xorStrings(L, F(R, keys[i]));
            L = tmp;
        }
        tmp = R + L;
        int[] IP_INV = {40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25};
        String ans = "";
        for (int i = 0; i < IP_INV.length; i++) {
            ans += tmp.charAt(IP_INV[i] - 1);
        }
        tmp = "";
        for (int i = 0; i < 8; i++) {
            BigInteger decimalValue = new BigInteger(ans.substring(0, 8), 2);
            String hexString = decimalValue.toString(16);
            tmp += hexString.length() == 1 ? '0' + hexString : hexString;
            ans = ans.substring(8);
        }
        return tmp;

    }
    public static String dec_Ceasr(String c, String key) {
        c = c.toLowerCase();// Remove non-alphabetic characters and convert to uppercase
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < c.length(); i++) {
            int idx = ((c.charAt(i) - 'a') - Integer.parseInt(key)) % 26;
            ciphertext.append((char) (idx + 'a'));
        }
        return ciphertext.toString();
    }
    public static String dec_Vigenere(String c, String key) {
        String res = "";
        c = c.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0; key.length() != c.length(); i++) {
            key += key.charAt(i);
        }
        for (int i = 0; i < c.length(); i++) {
            if (Character.isAlphabetic(c.charAt(i))) {
                res += (char) (((c.charAt(i) - 'a') - (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += c.charAt(i);
            }
        }
        return res.toUpperCase();
    }
    public static String dec_Autokey(String c, String key) {
        String res = "";
        c = c.toLowerCase();
        key = key.toLowerCase();
        int x=0;
        for (int i = 0; i < c.length(); i++) {
            if (Character.isAlphabetic(c.charAt(i))) {
                res += (char) (((c.charAt(i) - 'a') - (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += c.charAt(i);
            }
            if(i==key.length()-1){
                key+=res.charAt(x);
                x++;
            }
        }
        return res.toUpperCase();
    }
    public static String dec_OneTimePad(String c, String key) {
        c = c.toLowerCase(); // Remove non-alphabetic characters and convert to uppercase
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < c.length(); i++) {
            int idx = c.charAt(i) - 'a';
            int k = key.charAt(i) - 'a';
            int p = idx ^ k;
            char pp = (char) (p + 'a');
            plaintext.append(pp);
        }
        return plaintext.toString();
    }
    public static String dec_RailFence(String c, String kkey) {
        int key =Integer.parseInt(kkey);
        char[][] rail = new char[key][c.length()];

        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '@');

        boolean dirDown = true;

        int row = 0, col = 0;

        for (int i = 0; i < c.length(); i++) {

            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;

            rail[row][col++] = '*';

            if (dirDown)
                row++;
            else
                row--;
        }

        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < c.length(); j++)
                if (rail[i][j] == '*' && index < c.length())
                    rail[i][j] = c.charAt(index++);

        StringBuilder result = new StringBuilder();

        row = 0;
        col = 0;
        for (int i = 0; i < c.length(); i++) {

            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;

            if (rail[row][col] != '*')
                result.append(rail[row][col++]);

            if (dirDown)
                row++;
            else
                row--;
        }
        return result.toString();
    }
    public static String dec_RowTransposition(String c, String key) {
        c = c.toLowerCase();
        int cols = key.length();
        int rows = (int) Math.ceil((double) c.length() / cols);
        char[][] matrix = new char[rows][cols];
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);
        int index = 0;
        for (char k : sortedKey) {
            int j = key.indexOf(k);
            for (int i = 0; i < rows; i++) {
                if (index < c.length()) {
                    matrix[i][j] = c.charAt(index);
                    index++;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != '@') {
                    res.append(matrix[i][j]);
                }
            }
        }
        return res.toString().toUpperCase();
    }
    public static String dec_end_Vernam(String c, String key) {
        String res = "";
        c = c.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0; i < c.length(); i++) {
            if (Character.isAlphabetic(c.charAt(i))) {
                res += (char) (((c.charAt(i) - 'a') - (key.charAt(i) - 'a')) % 26 + 'a');
            } else {
                res += c.charAt(i);
            }
        }
        return res.toUpperCase();
    }

    public static String F(String R, String K) {
        int[] eBitTable = {
                32, 1, 2, 3, 4, 5, 4, 5,
                6, 7, 8, 9, 8, 9, 10, 11,
                12, 13, 12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21, 20, 21,
                22, 23, 24, 25, 24, 25, 26, 27,
                28, 29, 28, 29, 30, 31, 32, 1
        };
        String tmp = "";
        for (int i = 0; i < eBitTable.length; i++) {
            tmp += R.charAt(eBitTable[i] - 1);
        }
        R = tmp;
        tmp = xorStrings(R, K);
        tmp = DataOfSboxs.S_Box(tmp);
        int[] p = {16, 7, 20, 21,
                29, 12, 28, 17,
                1, 15, 23, 26,
                5, 18, 31, 10,
                2, 8, 24, 14,
                32, 27, 3, 9,
                19, 13, 30, 6,
                22, 11, 4, 25};
        String ans = "";
        for (int i = 0; i < p.length; i++) {
            ans += tmp.charAt(p[i] - 1);
        }
        return ans;
    }

    public static String multi(int mat1[][], int mat2[][], int r1, int c1, int r2, int c2) {
        if (c1 != r2) {
            System.out.println("# of columns in Mat1 must = # of row in mat 2");
            return "";
        }
        int[][] rslt = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                rslt[i][j] = 0;
                for (int k = 0; k < r2; k++) {
                    rslt[i][j] += (mat1[i][k] * mat2[k][j]) % 26;
                    rslt[i][j] %= 26;
                }
            }
        }
        String ans = "";
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                ans += (char) (rslt[i][j] + 'a');
            }
        }
        return ans;
    }

    private static int Inverse_of_num(int val) {

        for (int i = 1; i < 26; i++) {
            if (((val * i) % 26) == 1)
                return i;
        }
        return -1;
    }

    private static int make_num_pos(int val) {
        while (val < 0) {
            val += 26;
        }
        return val;
    }

    public static String xorStrings(String s1, String s2) {
        String result = "";
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                result += "0";
            } else {
                result += "1";
            }
        }
        return result;
    }

    public static String hexToBinary(String hex) {
        String binary = "";
        hex = hex.toUpperCase();

        HashMap<Character, String> hashMap = new HashMap<Character, String>();
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");
        char ch;
        for (int i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }

    public static String[] DES_Genertor_Key(String key) {
        key = hexToBinary(key);
        int[] pc1 = {57, 49, 41, 33, 25, 17, 9,
                1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14, 6, 61, 53, 45, 37, 29,
                21, 13, 5, 28, 20, 12, 4};
        String C = "", D = "";
        for (int i = 0; i < 56; i++) {
            if (i < 28)
                C += key.charAt(pc1[i] - 1);
            else
                D += key.charAt(pc1[i] - 1);
        }
        HashMap<Integer, Boolean> arr = new HashMap<>(16);
        arr.put(1, true);
        arr.put(2, true);
        arr.put(9, true);
        arr.put(16, true);
        String[] keys = new String[16];
        for (int i = 1; i <= 16; i++) {
            if (arr.containsKey(i)) {
                C = C.substring(1) + C.charAt(0);
                D = D.substring(1) + D.charAt(0);
            } else {
                C = C.substring(2) + C.charAt(0) + C.charAt(1);
                D = D.substring(2) + D.charAt(0) + D.charAt(1);
            }
            keys[i - 1] = C + D;
        }

        int[] pc2 = {14, 17, 11, 24, 1, 5,
                3, 28, 15, 6, 21, 10,
                23, 19, 12, 4, 26, 8,
                16, 7, 27, 20, 13, 2,
                41, 52, 31, 37, 47, 55,
                30, 40, 51, 45, 33, 48,
                44, 49, 39, 56, 34, 53,
                46, 42, 50, 36, 29, 32};
        C = "";

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 48; j++) {
                C += keys[i].charAt(pc2[j] - 1);
            }
            keys[i] = C;
            C = "";
        }
        return keys;
    }
    public static String generateKey(int length) {
        StringBuilder key = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int r = rand.nextInt(26);
            char c = (char) (r + 'a');
            key.append(c);
        }
        return key.toString();
    }




}

























