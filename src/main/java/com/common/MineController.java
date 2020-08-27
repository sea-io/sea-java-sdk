    private void enableButtonsAndFields() {
        progressIndicator.setVisible(false);
        registerButton.setDisable(false);
        loginButton.setDisable(false);
        this.username.setDisable(false);
        this.password.setDisable(false);
        this.label.setVisible(true);
        this.label.setText("Unknown username/password please register or try again.");
        this.label.setFont(Font.font(14));
    }

    private boolean checkDatabase(String username, String password) {
        try {

             PrintWriter out = new PrintWriter(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.write(loginRequestProtocol + "\n");
            out.flush();
            out.write(username + "\n");
            out.flush();
            out.write(password + "\n");
            out.flush();
            System.out.println("i got here");
            in.mark(1);
            byte returnedValue = (byte) in.read();
            System.out.println("I got value : "  + returnedValue);
            System.out.println("I got value : "  + returnedValue);
            System.out.println("I got value : "  + returnedValue);
            in.reset();
            return returnedValue == 1;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return false;

    }
