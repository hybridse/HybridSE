package org.arguslab.native_leak;

import android.os.Bundle;
import android.content.Context;
import android.view.View; 
import java.io.FileDescriptor;

public class MainActivityWrapper {

	public static void main(String[] args) {
		Bundle bd = new Bundle();
		(new MainActivity()).onCreate(bd);
		MainActivityWrapper().runRequest();
	}

	static void runRequest() {
		int[] intArray = {10,20,30,40,50,60,70,80};
		String[] strArray = new String[]{};
		(new MainActivity()).onRequestPermissionsResult(1, strArray, intArray);
	}
}