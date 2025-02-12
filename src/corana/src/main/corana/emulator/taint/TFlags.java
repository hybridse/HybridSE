package main.corana.emulator.taint;

import main.corana.pojos.BitBool;

public class TFlags {
    public Boolean N, Z, C, V, Q, GE;

    public TFlags(boolean n, boolean z, boolean c, boolean v, boolean q, boolean ge) {
        N = n;
        Z = z;
        C = c;
        V = v;
        Q = q;
        GE = ge;
    }
}
