<?php
unset($_COOKIE['discord_groups']);
setcookie("discord_groups", "", time() - 6000);
header("Location: https://discordgroups.weeryan17.tk");