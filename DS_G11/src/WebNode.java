import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
 public WebNode parent;
 public ArrayList<WebNode> children;
 public WebPage webPage; //child element
 public double nodeScore;//main element This node's score += all its children¡¦s nodeScore
 
 public WebNode(WebPage webPage){
  this.webPage = webPage;
  this.children = new ArrayList<WebNode>();
 }
 public double setNodeScore(KeywordList keywords) throws IOException{
  //this method should be called in post-order mode
  nodeScore=0;
  //1. compute webPage score
  webPage.setScore(keywords);
  //2. set webPage score to nodeScore
  nodeScore=webPage.score;
  
  //3. nodeScore += all children¡¦s nodeScore 
  for(WebNode child : children)
  {
   nodeScore+=child.nodeScore;
  }
  return nodeScore; 
   
 }
 
 public void addChild(WebNode child){
  //add the WebNode to its children list
  this.children.add(child);
  child.parent = this;
 }
 
 public boolean isTheLastChild(){
  if(this.parent == null) return true;
  ArrayList<WebNode> siblings = this.parent.children;
  
  return this.equals(siblings.get(siblings.size() - 1));
 }
 
 public int getDepth(){
  int retVal = 1;
  WebNode currNode = this;
  while(currNode.parent!=null){
   retVal ++;
   currNode = currNode.parent;
  }
  return retVal;
 }
}
