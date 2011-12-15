import jenkins.model.*

instance = jenkins.model.Jenkins.instance;
/*

Here's how this works.

You change the "thingyouarecloning" to be the name of the thing you want to clone.

If one were so inclinded, you could change line 20 to be a regex or even String.contains().

Then plug in the version string for the "newbranch" string object.

*/
String thingyouarecloning = "nameofjob";
String newbranch = "2.0";

for(item in Jenkins.instance.items) 
{
  if(item.name.equals(thingyouarecloning))
    {
		println("Ok, found " + thingyouarecloning + ", about to clone that");
		println( item.getScm().getBranch());
		if(item.getScm().getType().contains("mercurial"))
		{
			instance.copy(item, item.name+"-"+newbranch);
					   
			String installation = item.scm.getInstallation();
			String source = item.scm.getSource();
			String branch = newbranch;
			String modules = item.scm.getModules();
			String subdir = item.scm.getSubdir();
			hudson.plugins.mercurial.browser.HgBrowser browser = item.scm.getBrowser();
			boolean clean = item.scm.isClean();
			hudson.plugins.mercurial.MercurialSCM newscm = new hudson.plugins.mercurial.MercurialSCM(installation, source, branch, modules, subdir, browser, clean);
			println( instance.getItem(item.name+"-"+newbranch).setScm(newscm));
		}
	}
}