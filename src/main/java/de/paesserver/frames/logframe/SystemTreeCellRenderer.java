package de.paesserver.frames.logframe;

import de.paesserver.journalLog.DatabaseSingleton;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SystemTreeCellRenderer extends DefaultTreeCellRenderer {
    private final int width = 20;
    private final int height = 20;
    final private ImageIcon galaxyIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Realistic-galaxy-map.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon systemIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/orrery_map.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/planet.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconAmmonia = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-ammonia-world.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconEarthLike = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-earth-like-world.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiant = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantAmmoniabasedLife = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-ammoniabased-life.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantClassI = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-class-I.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantClassII = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-class-II.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantClassIII = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-class-III.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantClassIV = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-class-IV.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantClassV = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-class-V.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantHelium = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-helium.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantWater = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-water.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconGasGiantWaterbasedLife = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-gas-giant-waterbased-life.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    final private ImageIcon planetIconIcy = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-icy.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconRocky = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-rocky.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconRockyIce = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-rocky-ice.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconRockyTerraformable = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-rocky-terraformable.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon planetIconWaterWorld = new ImageIcon(new ImageIcon("de.paesserver/planets/planet-water-world.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    final private ImageIcon markerIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Map-galaxy-map.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    final private ImageIcon starIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/star.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    final private ImageIcon asteroidIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/asteroids.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        SystemTreeUserObject userObject = (SystemTreeUserObject) node.getUserObject();

        ImageIcon icon = markerIcon;
        switch (userObject.bodyType) {
            case Galaxy:
                icon = galaxyIcon;
                break;
            case System:
                icon = systemIcon;
                break;
            case Planet:
                switch (userObject.planetClass) {
                    case AmmoniaWorld:
                        icon = planetIconAmmonia;
                        break;
                    case EarthlikeWorld:
                        icon = planetIconEarthLike;
                        break;
                    case WaterWorld:
                        icon = planetIconWaterWorld;
                        break;
                    case IcyBody:
                        icon = planetIconIcy;
                        break;
                    case RockyBody:
                    case HighMetalContentPlanet:
                        String query = "SELECT TerraformState FROM PLANET WHERE BodyName = ?";
                        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)) {
                            statement.setString(1, userObject.nodeName);

                            ResultSet resultSet = statement.executeQuery();
                            resultSet.next();
                            if (resultSet.getString("TerraformState").equals("Terraformable")) {
                                icon = planetIconRockyTerraformable;
                                break;
                            }

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                        }
                    case MetalRichBody:
                        icon = planetIconRocky;
                        break;
                    case RockyIceBody:
                        icon = planetIconRockyIce;
                        break;
                    case ClassIGasGiant:
                        icon = planetIconGasGiantClassI;
                        break;
                    case ClassIIGasGiant:
                        icon = planetIconGasGiantClassII;
                        break;
                    case ClassIIIGasGiant:
                        icon = planetIconGasGiantClassIII;
                        break;
                    case ClassIVGasGiant:
                        icon = planetIconGasGiantClassIV;
                        break;
                    case ClassVGasGiant:
                        icon = planetIconGasGiantClassV;
                        break;
                    case GasGiantwithAmmoniabasedLife:
                        icon = planetIconGasGiantAmmoniabasedLife;
                        break;
                    case GasGiantwithWaterbasedLife:
                        icon = planetIconGasGiantWaterbasedLife;
                        break;
                    case WaterGiant:
                        icon = planetIconGasGiantWater;
                        break;
                    case HeliumRichGasGiant:
                        icon = planetIconGasGiantHelium;
                        break;
                    default:
                        icon = planetIcon;
                }
                break;
            case Star:
                icon = starIcon;
                break;
            case BeltCluster:
            case Ring:
                icon = asteroidIcon;
                break;
        }
        setIcon(icon);

        return this;
    }
}
