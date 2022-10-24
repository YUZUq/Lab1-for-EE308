package LAB1;

import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Scanner;

public class Lab1 {


    public static void main(String[] args) throws Exception {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Pleaase input the path of the code file.");
		String path=sc.nextLine();
		System.out.println("Pleaase input the completion level.");
		int level = Integer.parseInt(sc.nextLine());			//ѯ���ļ���ַ������ȼ�
    	Lab1 l = new Lab1();
    	String s = l.read(path);			//�ַ�����ʽ���ļ�
    	if (level == 1) {			//���ݵȼ���ͬ�������
            lv1(s);
        } else if (level == 2) {
        	lv2(s);
        } else if (level == 3) {
        	lv3(s);
        } else if (level == 4) {
        	lv4(s);
        }
    	sc.close();
    }

    public String read(String url) throws IOException {
        File file = new File(url);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static void lv1(String str) {
        int count = 0;
        String keyword[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for",
        		            "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef",
        		            "unsigned", "union", "void", "volatile", "while"};
        for (int i = 0; i < keyword.length; i++) {
            count += match(str, keyword[i]);
        }
        System.out.println("total num: " + count);
    }


    public static void lv2(String str) {
    	lv1(str);
        System.out.println("switch num: " + match(str, "switch"));
        String[] temp = str.split("switch");
        System.out.print("case num: ");
        for (int i = 1; i < temp.length; i++) {
            System.out.print(match(temp[i], "case") + " ");
        }
        System.out.println();
    }

    public static int match(String str, String target) {			//���ҹؼ���
        int start = 0;
        int count = 0;
        int length = target.length();
        while (true) {
        	if(str.indexOf(target,start)!=-1 && !str.substring(str.indexOf(target,start)-1, str.indexOf(target,start)).matches("[a-zA-Z]")
        			&& !str.substring(str.indexOf(target,start)+length, str.indexOf(target,start)+length+1).matches("[a-zA-Z]")) {
        		//�ؼ���ǰ�����Ϊ����ĸ����ȷ��Ϊ�ؼ���
        		count++;
        		start = str.indexOf(target,start);
        		start += length;
        	} else {
        		break;
        	}
        }
        return count;
    }

    public static void lv3(String str) {
    	lv2(str);
        int count = match2(str, 3);
        System.out.println("if-else num: " + count);
    }

    public static void lv4(String str) {
    	lv3(str);
        int count = match2(str, 4);
        System.out.println("if-elseif-else num: " + count);
    }

    public static int match2(String str, int level) {
        Stack<String> s1 = new Stack();
        Pattern pattern = Pattern.compile("else *if|else|if");
        Matcher matcher = pattern.matcher(str);
        int esif = 0;
        int ifes = 0;
        boolean noEsif = true;
        while (matcher.find()) {
            String subs = str.substring(matcher.start(), matcher.end());
            if (!subs.equals("else")) {			//��ջ�д�����if��/��else if��
                s1.push(subs);
            } else {			//��������else��
                while (!s1.peek().equals("if")) {			//ջ���һ��Ϊ��else if����˵����if else if�ṹ
                    s1.pop();
                    if (noEsif) {
                        esif++;
                        noEsif = false;
                    }
                }
                if (noEsif == true) {			//ջ���һ��Ϊ��if����˵����if else�ṹ
                    ifes++;
                }
                s1.pop();
                noEsif = true;
            }
        }
        if (level == 3) {
            return ifes;
        } else {
            return esif;
        }
    }
}