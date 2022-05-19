package com.github.sergey_kornyushin.presentation.films_list;

import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;

public class FilmsListView$$State extends MvpViewState<FilmsListView> implements FilmsListView {
	@Override
	public void showError(String message) {
		ShowErrorCommand showErrorCommand = new ShowErrorCommand(message);
		this.viewCommands.beforeApply(showErrorCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmsListView view : this.views) {
			view.showError(message);
		}

		this.viewCommands.afterApply(showErrorCommand);
	}

	@Override
	public void showLoading(boolean isLoading) {
		ShowLoadingCommand showLoadingCommand = new ShowLoadingCommand(isLoading);
		this.viewCommands.beforeApply(showLoadingCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmsListView view : this.views) {
			view.showLoading(isLoading);
		}

		this.viewCommands.afterApply(showLoadingCommand);
	}

	@Override
	public void fillRVList(List<RVFilmItem> filmsList) {
		FillRVListCommand fillRVListCommand = new FillRVListCommand(filmsList);
		this.viewCommands.beforeApply(fillRVListCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmsListView view : this.views) {
			view.fillRVList(filmsList);
		}

		this.viewCommands.afterApply(fillRVListCommand);
	}

	public class ShowErrorCommand extends ViewCommand<FilmsListView> {
		public final String message;

		ShowErrorCommand(String message) {
			super("showError", OneExecutionStateStrategy.class);

			this.message = message;
		}

		@Override
		public void apply(FilmsListView mvpView) {
			mvpView.showError(message);
		}
	}

	public class ShowLoadingCommand extends ViewCommand<FilmsListView> {
		public final boolean isLoading;

		ShowLoadingCommand(boolean isLoading) {
			super("showLoading", AddToEndSingleStrategy.class);

			this.isLoading = isLoading;
		}

		@Override
		public void apply(FilmsListView mvpView) {
			mvpView.showLoading(isLoading);
		}
	}

	public class FillRVListCommand extends ViewCommand<FilmsListView> {
		public final List<RVFilmItem> filmsList;

		FillRVListCommand(List<RVFilmItem> filmsList) {
			super("fillRVList", AddToEndSingleStrategy.class);

			this.filmsList = filmsList;
		}

		@Override
		public void apply(FilmsListView mvpView) {
			mvpView.fillRVList(filmsList);
		}
	}
}
