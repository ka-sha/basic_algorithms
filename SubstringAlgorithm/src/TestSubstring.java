public class TestSubstring {
    public static void main(String[] args) {

        String T1 = "qawsedcftgyhujikolazsxdcfvgbhjndcfmkl", P1 = "dcf";

        System.out.println("Подстрока 'dcf' в строке 'qawsedcftgyhujikolazsxdcfvgbhjndcfmkl' по алгоритму Бойера-Мура: " + Substring.BoyerMoore(T1, P1));
        System.out.println("Подстрока 'dcf' в строке 'qawsedcftgyhujikolazsxdcfvgbhjndcfmkl' по алгоритму Робина-Карпа: " + Substring.RobinKarp(T1, P1));
        System.out.println("Подстрока 'dcf' в строке 'qawsedcftgyhujikolazsxdcfvgbhjndcfmkl' по алгоритму Кнута-Морриа-Пратта: " + Substring.KnuthMorrisPratt(T1, P1));
        System.out.println("Подстрока 'dcf' в строке 'qawsedcftgyhujikolazsxdcfvgbhjndcfmkl' по алгоритму на основе конечного автомата: " + Substring.automa(T1, P1));

        String T2 = "abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbbbbbbaabbaa", P2 = "aabbaa";

        System.out.println("Подстрока 'aabbaa' в строке 'abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbbbbbbaabbaa' по алгоритму Бойера-Мура: " + Substring.BoyerMoore(T2, P2));
        System.out.println("Подстрока 'aabbaa' в строке 'abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbbbbbbaabbaa' по алгоритму Робина-Карпа: " + Substring.RobinKarp(T2, P2));
        System.out.println("Подстрока 'aabbaa' в строке 'abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbbbbbbaabbaa' по алгоритму Кнута-Морриа-Пратта: " + Substring.KnuthMorrisPratt(T2, P2));
        System.out.println("Подстрока 'aabbaa' в строке 'abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbbbbbbaabbaa' по алгоритму на основе конечного автомата: " + Substring.automa(T2, P2));

        String T3 = "hadbhad", P3 = "";

        System.out.println("Пустая строка в строке 'hadbhad' по алгоритму Бойера-Мура: " + Substring.BoyerMoore(T3, P3));
        System.out.println("Пустая строка в строке 'hadbhad' по алгоритму Робина-Карпа: " + Substring.RobinKarp(T3, P3));
        System.out.println("Пустая строка в строке 'hadbhad' по алгоритму Кнута-Морриа-Пратта: " + Substring.KnuthMorrisPratt(T3, P3));
        System.out.println("Пустая строка в строке 'hadbhad' по алгоритму на основе конечного автомата: " + Substring.automa(T3, P3));

        String T4 = "asd", P4 = "kcjdsfhsafbjhsbhb";

        System.out.println("Подстрока 'kcjdsfhsafbjhsbhb' в строке 'asd' по алгоритму Бойера-Мура: " + Substring.BoyerMoore(T4, P4));
        System.out.println("Подстрока 'kcjdsfhsafbjhsbhb' в строке 'asd' по алгоритму Робина-Карпа: " + Substring.RobinKarp(T4, P4));
        System.out.println("Подстрока 'kcjdsfhsafbjhsbhb' в строке 'asd' по алгоритму Кнута-Морриа-Пратта: " + Substring.KnuthMorrisPratt(T4, P4));
        System.out.println("Подстрока 'kcjdsfhsafbjhsbhb' в строке 'asd' по алгоритму на основе конечного автомата: " + Substring.automa(T4, P4));

        String T5 = "xyz", P5 = "xyz";
        System.out.println("Подстрока 'xyz' в строке 'xyz' по алгоритму Бойера-Мура: " + Substring.BoyerMoore(T5, P5));
        System.out.println("Подстрока 'xyz' в строке 'xyz' по алгоритму Робина-Карпа: " + Substring.RobinKarp(T5, P5));
        System.out.println("Подстрока 'xyz' в строке 'xyz' по алгоритму Кнута-Морриа-Пратта: " + Substring.KnuthMorrisPratt(T5, P5));
        System.out.println("Подстрока 'xyz' в строке 'xyz' по алгоритму на основе конечного автомата: " + Substring.automa(T5, P5));
    }
}