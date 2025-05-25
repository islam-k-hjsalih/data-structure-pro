public class polynomial {
    islamLinkedList islam = new islamLinkedList();

    public void addTerm(int coef, int power) {
        islam.addlast(new term(coef, power));
    }

    public polynomial add(polynomial other) {
        polynomial result = new polynomial();

        // انسخ كل الحدود من this
        for (int i = 0; i < this.islam.size(); i++) {
            term t1 = (term) this.islam.getIndex(i);
            result.addTerm(t1.c, t1.p);
        }

        // مر على حدود other
        for (int i = 0; i < other.islam.size(); i++) {
            term t2 = (term) other.islam.getIndex(i);
            boolean found = false;
            for (int j = 0; j < result.islam.size(); j++) {
                term tRes = (term) result.islam.getIndex(j);
                if (tRes.p == t2.p) {
                    tRes.c += t2.c;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.addTerm(t2.c, t2.p);
            }
        }

        return result;
    }

    public polynomial subtract(polynomial other) {
        polynomial result = this.copy();
        for (int i = 0; i < other.islam.size(); i++) {
            term t2 = (term) other.islam.getIndex(i);
            boolean found = false;
            for (int j = 0; j < result.islam.size(); j++) {
                term tRes = (term) result.islam.getIndex(j);
                if (tRes.p == t2.p) {
                    tRes.c -= t2.c;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.addTerm(-t2.c, t2.p);
            }
        }
        return result;
    }

    public polynomial multiply(polynomial other) {
        polynomial result = new polynomial();
        for (int i = 0; i < this.islam.size(); i++) {
            term t1 = (term) this.islam.getIndex(i);
            for (int j = 0; j < other.islam.size(); j++) {
                term t2 = (term) other.islam.getIndex(j);
                int newC = t1.c * t2.c;
                int newP = t1.p + t2.p;
                boolean found = false;
                for (int k = 0; k < result.islam.size(); k++) {
                    term tRes = (term) result.islam.getIndex(k);
                    if (tRes.p == newP) {
                        tRes.c += newC;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.addTerm(newC, newP);
                }
            }
        }
        return result;
    }

    public boolean isZero() {
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            if (t.c != 0) return false;
        }
        return true;
    }

    public polynomial copy() {
        polynomial result = new polynomial();
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            result.addTerm(t.c, t.p);
        }
        return result;
    }

    public int degree() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            if (t.p > max) max = t.p;
        }
        return max;
    }

    public term leadingTerm() {
        term max = null;
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            if (max == null || t.p > max.p) {
                max = t;
            }
        }
        return max;
    }

    public polynomial divide(polynomial divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        polynomial dividend = this.copy();
        polynomial quotient = new polynomial();

        while (!dividend.isZero() && dividend.degree() >= divisor.degree()) {
            term leadDvd = dividend.leadingTerm();
            term leadDvs = divisor.leadingTerm();

            int c = leadDvd.c / leadDvs.c;
            int p = leadDvd.p - leadDvs.p;

            term tQuot = new term(c, p);
            quotient.addTerm(c, p);

            polynomial temp = new polynomial();
            temp.addTerm(c, p);
            polynomial toSub = temp.multiply(divisor);

            dividend = dividend.subtract(toSub);
        }

        return quotient;
    }



    public void displayPostfix() {
        islamStacks stack = new islamStacks();
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            if (t.c == 0) continue;
            stack.push(t.toString());
        }
        while (stack.list.size > 0) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public void displayPrefix() {
        islamStacks stack = new islamStacks();
        for (int i = islam.size() - 1; i >= 0; i--) {
            term t = (term) islam.getIndex(i);
            if (t.c == 0) continue;
            stack.push(t.toString());
        }
        while (stack.list.size > 0) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println(); // 7. سطر جديد بعد الطباعة
    }

    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i < islam.size(); i++) {
            term t = (term) islam.getIndex(i);
            result += t.c * Math.pow(x, t.p);
        }
        return result;
    }

}
