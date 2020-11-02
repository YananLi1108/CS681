package edu.umb.cs681.hw9;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {
	
	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}
	
	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}
	
	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}
	
	public void run() {
		System.out.println("\nThread #" + Thread.currentThread().getId());
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("\n--->"+child.getName());
			LinkedList<ApfsElement> child1Children =child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println("--->"+child1.getName());
				LinkedList<ApfsElement> child2Children =child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println("--->"+child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {
		
		APFS apfs = new APFS("APFS File System");
		
		apfs.initFileSystem("Local Disk", 10000);		
		ApfsDirectory root = apfs.getRootDir();
		
		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile file1, file2;		
		file1 = new ApfsFile(applications, "file1", 40);
		file2 = new ApfsFile(applications, "file2", 40);
		applications.appendChild(file1);
		applications.appendChild(file2);
		//System.out.println("APFS Class Successfully Run!!");

		Thread t1 = new Thread(apfs);				
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ApfsDirectory home  = new ApfsDirectory(root, "home");
		root.appendChild((home));
		ApfsFile file3, file4;		
		file3 = new ApfsFile(home, "file3", 200);
		file4 = new ApfsFile(home, "file4", 300);
		home.appendChild(file3);
		home.appendChild(file4);
		//System.out.println("APFS Class Successfully Run!!");

		Thread t2 = new Thread(apfs);				
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
		
	
	}
}