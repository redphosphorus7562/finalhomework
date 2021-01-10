public class rollbackCommit extends commitmanage {
	String commit_id;

	public String rollBackCommit(String commit_id) throws Exception {
		for (String branchname : commitPoint.keySet()) {
			while (branchname != null) {
				if(commitNodePoint.commit_id == commit_id) {
					commitmanage.HEAD = branchname;
					return commitmanage.HEAD;
				}
			}
		}
	}
}


