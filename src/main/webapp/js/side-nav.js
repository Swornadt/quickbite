/**
 * 
 */

document.addEventListener("DOMContentLoaded",function(){
	const navLinks=document.querySelectorAll('.side-nav-content a');
	navLinks.forEach(link=>{
		link.addEventListener('click',function(e){
			e.preventDefault(); //This stops the page from refreshing.
			navLinks.forEach(item=>item.classList.remove('active'));
			this.classList.add('active');
		})
	})
});