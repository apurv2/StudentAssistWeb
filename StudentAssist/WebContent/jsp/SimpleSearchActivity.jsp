
<style>
.dropdown-menu {
	left: initial;
}
</style>


<div ng-app="ui.bootstrap.demo" ng-controller="simpleSearchController" >


	<div class="row" style="max-width: 95%; overflow-y: hidden;max-height:90%;">

		<div class="large-3 columns well"
			style="background: white; max-height: 90%; padding-right: 0; padding-left: 0; height:88%">


			<%@include file="SimpleSearchFragment.html"%>


		</div>

		<div class="large-9 columns adDetailsInfo "  style="height:88%;border-radius:6px;">

			<div id="sidebar">

				<%@include file="adDetails.html"%>

			</div>

		</div>


	</div>










</div>