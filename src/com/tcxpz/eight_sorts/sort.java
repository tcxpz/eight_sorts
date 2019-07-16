package com.tcxpz.eight_sorts;

import org.junit.Test;

public class sort {
	public static void main(String[] args) {
		int[] array = {7,4,2,3,9,6,11,1,10,5,8};
        System.out.print("before sort:");
        for (int num:array)
            System.out.print(num+" ");
        System.out.println();
/**********************排序算法**********************/
        //bubbleSort(array);
        //optimizedBubbleSort(array);
        //selectionSort(array);
        //insertSort(array);
        //shellSort(array);
        //quickSort(array,0,array.length-1);
        //heapSort(array);
        bucketSort(array);
/*************************************************/
        System.out.print("after  sort:");
        for (int num:array)
        	System.out.print(num+" ");
        System.out.println();
	}
/************************bubbleSort************************/
	private static void bubbleSort(int[] a) {
		for(int i=1;i<a.length;i++){
			for(int j=0;j<a.length-i;j++){
				if(a[j]>a[j+1]){
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		System.out.println("bubbleSort");
	}
/************************optimizedBubbleSort************************/
	//如果在某趟排序中没有发生交换位置，那么我们可以认为该数组已经排好序了
	private static void optimizedBubbleSort(int[] a){
		boolean flag;
		int temp;
		for(int i=1;i<a.length;i++){
			flag = false;
			for(int j=0;j<a.length-i;j++){
				if(a[j]>a[j+1]){
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					flag = true;
				}
			}
			if(flag == false){
				break;
			}
		}
		System.out.println("optimizedBubbleSort");
	}
/************************selectionSort************************/
	private static void selectionSort(int[] a){
		int temp;
		int min;
		for(int i=0;i<a.length-1;i++){
			min = a.length-1;
			for(int j=i;j<a.length-1;j++){
				if(a[j]<a[min]){
					min = j;
				}
			}
			temp = a[i];
			a[i] = a[min];
			a[min] = temp;
		}
		System.out.println("selectionSort");
	}
/************************insertSort************************/
	private static void insertSort(int[] a){
		int temp;
		for(int i=1;i<a.length;i++){
			temp = a[i];
			int j = i-1;
			while(j>=0&&a[j]>temp){
				a[j+1]=a[j];
				j--;
			}
			a[j+1] = temp;
		}
		System.out.println("insertSort");
	}
/************************shellSort************************/
	private static void shellSort(int[] a){
		for(int gap=a.length/2;gap>0;gap/=2){
			for(int i=gap;i<a.length;i++){
				int temp = a[i];
				int j=i-gap;
				while(j>=0&&a[j]>temp){
					a[j+gap]=a[j];
					j = j-gap;
				}
				a[j+gap]=temp;
			}
		}
		System.out.println("shellSort");
	}
/************************quickSort************************/
	private static void quickSort(int[] a,int l,int r){
		System.out.println("quickSort["+l+","+r+"]");
		int temp;
		int i=l;
		int j=r;
		int pivot = (i+j)/2;
		while(i<j){
			while(a[i]<a[pivot]){
				i++;
			}
			while(a[j]>a[pivot]){
				j--;
			}
			if(i<=j){
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		if(l<j){
			quickSort(a,l,j);
		}
		if(i<r){
			quickSort(a,i,r);
		}
	}
/************************heapSort************************/
	private static void heapSort(int[] a){
		int temp;
		for(int i=0;i<a.length;i++){
			maxHeapify(a,a.length-i);
			temp = a[0];
			a[0] = a[a.length-1-i];
			a[a.length-1-i]=temp;
		}
		System.out.println("heapSort");
	}
	private static void maxHeapify(int[] a,int size){
		for(int i=size-1;i>=0;i--){
			heapify(a,i,size);
		}
	}
	private static void heapify(int[] a, int currentRootNode, int size) {
		if (currentRootNode < size) {
			int left = 2 * currentRootNode + 1;
			int right = 2 * currentRootNode + 2;
			//把当前父节点位置看成是最大的
			int max = currentRootNode;
			if (left < size) {
				//如果比当前根元素要大，记录它的位置
				if (a[max] < a[left]) {
					max = left;
				}
			}
			if (right < size) {
				//如果比当前根元素要大，记录它的位置
				if (a[max] < a[right]) {
					max = right;
				}
			}
			//如果最大的不是根元素位置，那么就交换
			if (max != currentRootNode) {
				int temp = a[max];
				a[max] = a[currentRootNode];
				a[currentRootNode] = temp;
				//继续比较，直到完成一次建堆
				heapify(a, max, size);
			}
		}		
	}
/************************bucketSort************************/
	private static void bucketSort(int[] a){
		int max = findMax(a,0,a.length-1);
		//需要遍历的次数由数组中的最大值来决定
		for(int i=1; max/i> 0;i=i*10){
			int[][] bucket = new int[a.length][10];
			for(int j=0;j<a.length;j++){
				int column = (a[j]/i)%(10);
				bucket[j][column]=a[j];
			}
			//回收桶里面的元素
			int num=-1;
			for(int k=0;k<10;k++){
				for(int j=0;j<a.length;j++){
					if(bucket[j][k]!=0){
						a[++num]=bucket[j][k];
					}
				}
			}
		}
	}
	private static int findMax(int[] a,int l,int r){
		if(l==r){
			return a[l];
		}			
		else{
			int max1 = a[l];
			int max2 = findMax(a,l+1,r);
			if(max1>max2)
				return max1;
			else 
				return max2;
		}	
	}
}
