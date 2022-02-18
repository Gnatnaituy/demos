void handler_1(int sig) {
	int old_errno = errno;
	
	if ((waitpid(-1, NULL, 0)) < 0) {
		sio_error("waitpid error");
	}

	sio_puts("Handler reaped child\n");
	Sleep(1);
	errno = old_errno;
}

int main() {
	int i, n;
	char buf[MAXBUF];

	if (signal(SIGCHILD, handler_1) == SIG_ERROR) {
		unix_error("signal error");
	}

	for (int i = 0; i < 3; i++) {
		if (Fork() == 0) {
			printf("Hello from child %d\n", (int) getpid());
			exit(0);
		}
	}

	if ((n = read(STDIN_FILENO, buf, size_of(buf))) < 0) {
		unix_error("read error");
	}

	printf("Parent processing input\n");

	while (1) {
	}

	exit(0);
}
